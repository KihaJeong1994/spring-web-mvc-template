package com.example.springwebmvctemplate.domain.user.mapper;

import com.example.springwebmvctemplate.domain.user.dto.UserDto;
import com.example.springwebmvctemplate.domain.user.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy =
        InjectionStrategy
            .CONSTRUCTOR) // https://mapstruct.org/documentation/stable/reference/html/#using-dependency-injection
public interface UserMapper {
  UserDto toDto(User user);
}
