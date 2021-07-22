package com.nubiform.userservice.service;

import com.nubiform.userservice.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserid(String userid);

    List<UserDto> getUserByAll();
}
