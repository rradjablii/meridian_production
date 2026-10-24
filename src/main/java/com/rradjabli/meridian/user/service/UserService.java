package com.rradjabli.meridian.user.service;

import com.rradjabli.meridian.user.dto.UserRequestDto;
import com.rradjabli.meridian.user.dto.UserResponseDto;
import com.rradjabli.meridian.user.entity.User;
import com.rradjabli.meridian.exception.UserAlreadyExistsException;
import com.rradjabli.meridian.user.mapper.UserMapper;
import com.rradjabli.meridian.user.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto registerNewUser(UserRequestDto user){

        User userEntity = UserMapper.mapToUser(user);
        userEntity.setEmail(userEntity.getEmail().trim().toLowerCase());

        if(userRepo.existsByEmail(userEntity.getEmail())){
            throw new UserAlreadyExistsException("User already exists");
        }

        userEntity.setRegisteredDate(Instant.now());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        User savedUser = userRepo.save(userEntity);

        return UserMapper.mapToResponse(userEntity);
    }

}
