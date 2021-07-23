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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user-service")
public class UserController {

    private final UserService userService;

    private final Environment environment;

    private final ModelMapper modelMapper;

    @GetMapping("/health-check")
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

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        log.debug("getUsers");
        return ResponseEntity.ok().body(userService.getUserByAll().stream()
                .map(userDto -> modelMapper.map(userDto, UserResponse.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/users/{userid}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userid) {
        log.debug("getUser: {}", userid);
        UserResponse userResponse = modelMapper.map(userService.getUserByUserid(userid), UserResponse.class);
        userResponse.setOrders(new ArrayList<>());
        return ResponseEntity.ok().body(userResponse);
    }
}
