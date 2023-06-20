package com.example.springwebmvctemplate.controller;

import com.example.springwebmvctemplate.domain.user.dto.SaveUserRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void saveUser(SaveUserRequest saveUserRequest){
        System.out.println(saveUserRequest.name());
        System.out.println(saveUserRequest.email());
        System.out.println(saveUserRequest.profile().getOriginalFilename());
    }
}
