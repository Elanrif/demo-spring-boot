package com.elanrif.inventory_management.controllers;

import com.elanrif.inventory_management.dto.UserDto;
import com.elanrif.inventory_management.dto.UserReqDto;
import com.elanrif.inventory_management.entities.User;
import com.elanrif.inventory_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
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
