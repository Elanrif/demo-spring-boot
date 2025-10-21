package com.elanrif.inventory_management.mapper;

import com.elanrif.inventory_management.dto.UserDto;
import com.elanrif.inventory_management.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMap {
    @Mapping(target = "password", ignore = true)
    UserDto toDto(User user);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto);
}
