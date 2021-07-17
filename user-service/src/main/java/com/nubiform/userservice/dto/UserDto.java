package com.nubiform.userservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private String username;

    private String password;

    private String email;

    private String userid;

    private String encryptedPassword;

    private LocalDateTime createdAt;
}
