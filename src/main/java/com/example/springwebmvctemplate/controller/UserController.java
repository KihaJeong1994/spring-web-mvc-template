package com.example.springwebmvctemplate.controller;

import com.example.springwebmvctemplate.common.feign.UserClient;
import com.example.springwebmvctemplate.domain.user.dto.SaveUserRequest;
import com.example.springwebmvctemplate.domain.user.dto.UpdateUserProfileRequest;
import com.example.springwebmvctemplate.domain.user.dto.UserDto;
import com.example.springwebmvctemplate.domain.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserService userService;
  private final UserClient userClient;

  @GetMapping("")
  public List<UserDto> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/feign")
  public List<UserDto> getUsersFromFeign() {
    return userClient.getUsers();
  }

  @GetMapping("/{id}")
  public UserDto getUserInfo(@PathVariable long id) {
    return userService.getUserById(id);
  }

  @PostMapping(
      value = "",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public UserDto saveUser(SaveUserRequest saveUserRequest) {
    return userService.saveUser(saveUserRequest);
  }

  @PutMapping(
      value = "/{id}",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public UserDto updateUserProfile(
      @PathVariable long id, UpdateUserProfileRequest updateUserProfileRequest) {
    return userService.updateUserProfile(id, updateUserProfileRequest);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deleteUserById(@PathVariable long id) {
    userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }
}
