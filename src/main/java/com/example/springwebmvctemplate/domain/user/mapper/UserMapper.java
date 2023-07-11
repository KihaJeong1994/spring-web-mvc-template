package com.example.springwebmvctemplate.domain.user.mapper;

import com.example.springwebmvctemplate.domain.user.dto.UserDto;
import com.example.springwebmvctemplate.domain.user.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserDto toDto(User user);
}
