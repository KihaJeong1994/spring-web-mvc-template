package com.example.springwebmvctemplate.controller;

import com.example.springwebmvctemplate.domain.user.dto.SaveUserRequest;
import com.example.springwebmvctemplate.domain.user.dto.UserDto;
import com.example.springwebmvctemplate.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }


    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UserDto saveUser(SaveUserRequest saveUserRequest){
        return userService.saveUser(saveUserRequest);
    }
}
