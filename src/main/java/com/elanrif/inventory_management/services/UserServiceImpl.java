package com.elanrif.inventory_management.services;

import com.elanrif.inventory_management.dto.UserDto;
import com.elanrif.inventory_management.dto.UserReqDto;
import com.elanrif.inventory_management.entities.User;
import com.elanrif.inventory_management.payload.request.SignupRequest;

import java.util.List;

public interface UserServiceImpl {
    List<User> getAllUsers(String order);
    User getUserById(Long id);
    User updateUser(UserReqDto userReqDto, Long id);
    UserDto login(UserReqDto userReqDto);
    void deleteUser(Long id);
    User register(SignupRequest signupRequest);
}
