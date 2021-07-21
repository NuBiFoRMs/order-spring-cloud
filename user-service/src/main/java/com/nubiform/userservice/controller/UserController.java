package com.nubiform.userservice.controller;

import com.nubiform.userservice.dto.UserDto;
import com.nubiform.userservice.service.UserService;
import com.nubiform.userservice.vo.UserRequest;
import com.nubiform.userservice.vo.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    private final Environment environment;

    private final ModelMapper modelMapper;

    @GetMapping("/user-service/health-check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s", environment.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return environment.getProperty("greeting.message");
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        log.debug("createUser: {}", userRequest);
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        UserResponse userResponse = modelMapper.map(userService.createUser(userDto), UserResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
