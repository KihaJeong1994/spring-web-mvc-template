package com.example.springwebmvctemplate.service;

import com.example.springwebmvctemplate.domain.user.dto.UserDto;
import com.example.springwebmvctemplate.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TransactionalTest {
    @Autowired
    private UserService userService;

    @Test
    void test_transactionalReadOnlyOnService(){
        userService.soutConnection();
        System.out.println("start transactionalReadOnlyOnService!!");
        List<UserDto> users = userService.transactionalReadOnlyOnService();
        System.out.println("end transactionalReadOnlyOnService!!");
        userService.soutConnection();
    }

    @Test
    void test_transactionalReadOnlyOnRepository(){
        userService.soutConnection();
        System.out.println("start transactionalReadOnlyOnRepository!!");
        List<UserDto> users = userService.transactionalReadOnlyOnRepository();
        System.out.println("end transactionalReadOnlyOnRepository!!");
        userService.soutConnection();
    }
}
