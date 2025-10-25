package com.elanrif.inventory_management.controllers;

import com.elanrif.inventory_management.dto.UserDto;
import com.elanrif.inventory_management.dto.UserReqDto;
import com.elanrif.inventory_management.entities.Category;
import com.elanrif.inventory_management.entities.User;
import com.elanrif.inventory_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(value = "order", required = false) String order) {
        return userService.getAllUsers(order);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public User register(@RequestBody UserReqDto userReqDto) {
        return userService.register(userReqDto);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserReqDto userReqDto) {
        return userService.login(userReqDto);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserReqDto userReqDto) {
        return userService.updateUser(userReqDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
