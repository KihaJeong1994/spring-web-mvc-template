package com.example.springwebmvctemplate.domain.user.service;

import com.example.springwebmvctemplate.domain.user.dto.SaveUserRequest;
import com.example.springwebmvctemplate.domain.user.dto.UpdateUserProfileRequest;
import com.example.springwebmvctemplate.domain.user.dto.UserDto;
import com.example.springwebmvctemplate.domain.user.entity.User;
import com.example.springwebmvctemplate.domain.user.mapper.UserMapper;
import com.example.springwebmvctemplate.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<UserDto> getUsers() {
    return userRepository.findAll().stream().map(userMapper::toDto).toList();
  }

  @Cacheable(value = "user", key = "#id")
  public UserDto getUserById(long id) {
    return userRepository.findById(id).map(userMapper::toDto).orElseThrow();
  }

  public UserDto saveUser(SaveUserRequest saveUserRequest) {
    User user =
        User.builder()
            .email(saveUserRequest.email())
            .name(saveUserRequest.name())
            .profileFileName(saveUserRequest.profile().getOriginalFilename())
            .build();
    User savedUser = userRepository.save(user);
    return userMapper.toDto(savedUser);
  }

  @CachePut(value = "user", key = "#id")
  public UserDto updateUserProfile(long id, UpdateUserProfileRequest updateUserProfileRequest) {
    User user = userRepository.findById(id).orElseThrow();
    user.updateProfile(updateUserProfileRequest.profile().getOriginalFilename());
    User savedUser = userRepository.save(user);
    return userMapper.toDto(savedUser);
  }

  @CacheEvict(value = "user", key = "#id")
  public void deleteUserById(long id) {
    userRepository.deleteById(id);
  }
}
