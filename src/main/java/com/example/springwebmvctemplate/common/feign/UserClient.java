package com.example.springwebmvctemplate.common.feign;

import com.example.springwebmvctemplate.domain.user.dto.UserDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user")
public interface UserClient {
  @GetMapping("")
  List<UserDto> getUsers();
}
