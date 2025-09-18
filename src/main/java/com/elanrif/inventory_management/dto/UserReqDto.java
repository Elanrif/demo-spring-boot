package com.elanrif.inventory_management.dto;

import lombok.Data;

@Data
public class UserReqDto {
    private String username;
    private String email;
    private String phone;
    private String address;
    private String password;
}
