package com.bookstore.dto;

import com.bookstore.entity.Role;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
