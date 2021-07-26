package com.nubiform.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String userName;

    private String email;

    private String userId;

    private List<OrderResponse> orders;
}
