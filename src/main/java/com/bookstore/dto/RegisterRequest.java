package com.bookstore.dto;

import com.bookstore.entity.*;

import lombok.Data;

@Data
public class RegisterRequest {

    private String userName;
    private String email;
    private String password;
    private Role role;
}
