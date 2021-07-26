package com.nubiform.userservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private String userName;

    private String password;

    private String email;

    private String userId;

    private String encryptedPassword;

    private LocalDateTime createdAt;
}
