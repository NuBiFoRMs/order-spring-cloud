package com.nubiform.userservice.controller;

import com.nubiform.userservice.dto.UserDto;
import com.nubiform.userservice.service.UserService;
import com.nubiform.userservice.vo.UserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    private final Environment environment;

    private final ModelMapper modelMapper;

    @GetMapping("/health-check")
    public String status() {
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return environment.getProperty("greeting.message");
    }

    @PostMapping("/users")
    public String createUser(@Valid @RequestBody UserRequest userRequest) {
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        userService.createUser(userDto);

        return "Create user method is called";
    }
}
