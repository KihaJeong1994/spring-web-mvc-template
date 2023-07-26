package com.example.springwebmvctemplate.domain.user.service;

import com.example.springwebmvctemplate.domain.user.dto.SaveUserRequest;
import com.example.springwebmvctemplate.domain.user.dto.UpdateUserProfileRequest;
import com.example.springwebmvctemplate.domain.user.dto.UserDto;
import com.example.springwebmvctemplate.domain.user.entity.User;
import com.example.springwebmvctemplate.domain.user.mapper.UserMapper;
import com.example.springwebmvctemplate.domain.user.repository.UserRepository;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DataSource dataSource;
    private final HikariDataSource hikariDataSource;

    public List<UserDto> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Cacheable(value = "user", key = "#id")
    public UserDto getUserById(long id){
        return userRepository.findById(id).map(userMapper::toDto).orElseThrow();
    }

    public UserDto saveUser(SaveUserRequest saveUserRequest){
        User user = User.builder()
                .email(saveUserRequest.email())
                .name(saveUserRequest.name())
                .profileFileName(saveUserRequest.profile().getOriginalFilename())
                .build();
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @CachePut(value = "user", key = "#id")
    public UserDto updateUserProfile(long id, UpdateUserProfileRequest updateUserProfileRequest){
        User user = userRepository.findById(id).orElseThrow();
        user.updateProfile(updateUserProfileRequest.profile().getOriginalFilename());
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @CacheEvict(value = "user", key = "#id")
    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<UserDto> transactionalReadOnlyOnService(){
        List<UserDto> userDtos = userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
        timeSleepAndPrintConnection();

        return userDtos;
    }

    public List<UserDto> transactionalReadOnlyOnRepository(){
        List<UserDto> userDtos = userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
        timeSleepAndPrintConnection();

        return userDtos;
    }

    private void timeSleepAndPrintConnection() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Define the method to be invoked every second
        Runnable task = () -> {
            // Replace this with the method you want to invoke
            soutConnection();
        };

        // Schedule the task to run every second, with an initial delay of 0 and a period of 1 second
        executor.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);

        // Sleep for 5 seconds to allow the task to run
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Shutdown the executor service after 5 seconds
        executor.shutdown();
    }

    public void soutConnection(){
        HikariPoolMXBean hikariPoolMXBean = hikariDataSource.getHikariPoolMXBean();
        System.out.println("activeConnections:"+hikariPoolMXBean.getActiveConnections()
                +", IdleConnections:"+hikariPoolMXBean.getIdleConnections()
                +", TotalConnections:"+hikariPoolMXBean.getTotalConnections()
        );

    }
}
