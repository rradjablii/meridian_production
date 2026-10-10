package com.rradjabli.meridian.user_service.service;

import com.rradjabli.meridian.user_service.dto.UserRequestDto;
import com.rradjabli.meridian.user_service.dto.UserResponseDto;
import com.rradjabli.meridian.user_service.entitiy.User;
import com.rradjabli.meridian.user_service.exception.UserAlreadyExistsException;
import com.rradjabli.meridian.user_service.mapper.UserMapper;
import com.rradjabli.meridian.user_service.repo.UserRepo;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<UserResponseDto> registerNewUser(UserRequestDto user){

        User userEntity = UserMapper.mapToUser(user);
        userEntity.setEmail(userEntity.getEmail().toLowerCase());

        if(userRepo.existsByEmail(userEntity.getEmail())){
            throw new UserAlreadyExistsException("User already exists");
        }

        userEntity.setRegisteredDate(Instant.now());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepo.save(userEntity);
        UserResponseDto response = UserMapper.mapToResponse(userEntity);
        return ResponseEntity.ok(response);
    }


}
