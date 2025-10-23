package com.elanrif.inventory_management.services;

import com.elanrif.inventory_management.dto.UserDto;
import com.elanrif.inventory_management.dto.UserReqDto;
import com.elanrif.inventory_management.entities.User;
import java.util.List;

public interface UserServiceImpl {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    User register(UserReqDto userReqDto);
    User updateUser(UserReqDto userReqDto, Long id);
    UserDto login(UserReqDto userReqDto);
    void deleteUser(Long id);
    List<User> fetchByOrderByIdDesc();
}
