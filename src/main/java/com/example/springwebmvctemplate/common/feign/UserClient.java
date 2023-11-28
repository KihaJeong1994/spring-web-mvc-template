package com.example.springwebmvctemplate.common.feign;

import com.example.springwebmvctemplate.domain.user.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "${feign.name}",url = "${feign.url}")
public interface UserClient {
    @GetMapping("")
    List<UserDto> getUsers();
}