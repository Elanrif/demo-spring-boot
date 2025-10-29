package com.elanrif.inventory_management.dto;

import com.elanrif.inventory_management.entities.Role;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private String address;
    private Set<Role> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
