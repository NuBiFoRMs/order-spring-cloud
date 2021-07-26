package com.nubiform.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRequest {

    @NotBlank
    @Size(min = 2)
    private String userName;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Email
    private String email;
}
