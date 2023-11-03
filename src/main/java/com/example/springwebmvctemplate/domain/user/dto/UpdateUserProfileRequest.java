package com.example.springwebmvctemplate.domain.user.dto;

import org.springframework.web.multipart.MultipartFile;

public record UpdateUserProfileRequest(MultipartFile profile) {}
