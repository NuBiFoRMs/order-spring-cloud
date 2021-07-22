package com.nubiform.userservice.service;

import com.nubiform.userservice.dto.UserDto;
import com.nubiform.userservice.entity.UserEntity;
import com.nubiform.userservice.exception.UsernameNotFoundException;
import com.nubiform.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserid(UUID.randomUUID().toString());
        userDto.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserid(String userid) {
        UserEntity userEntity = userRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException());
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> getUserByAll() {
        return userRepository.findAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }
}
