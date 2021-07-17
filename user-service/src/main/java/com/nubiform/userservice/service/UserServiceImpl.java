package com.nubiform.userservice.service;

import com.nubiform.userservice.dto.UserDto;
import com.nubiform.userservice.entity.UserEntity;
import com.nubiform.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserid(UUID.randomUUID().toString());
        userDto.setEncryptedPassword("encrypted_password");
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userRepository.save(userEntity);
        return userDto;
    }
}
