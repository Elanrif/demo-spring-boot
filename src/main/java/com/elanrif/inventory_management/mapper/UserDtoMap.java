package com.elanrif.inventory_management.mapper;

import com.elanrif.inventory_management.dto.UserDto;
import com.elanrif.inventory_management.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMap {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
