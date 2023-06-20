package com.example.springwebmvctemplate.domain.user.dto;

import org.springframework.web.multipart.MultipartFile;

public record SaveUserRequest (
        String email,
        String name,
        MultipartFile profile
){
}
