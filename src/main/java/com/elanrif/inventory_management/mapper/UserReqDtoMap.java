package com.elanrif.inventory_management.mapper;

import com.elanrif.inventory_management.dto.UserReqDto;
import com.elanrif.inventory_management.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserReqDtoMap {
    UserReqDto toDto(User user);
    User toEntity(UserReqDto userDto);
}

