package com.rradjabli.meridian.user_service.service;

import com.rradjabli.meridian.user_service.dto.UserRequestDto;
import com.rradjabli.meridian.user_service.dto.UserResponseDto;
import com.rradjabli.meridian.user_service.entitiy.User;
import com.rradjabli.meridian.user_service.mapper.UserMapper;
import com.rradjabli.meridian.user_service.repo.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<UserResponseDto> registerNewUser(UserRequestDto user){
        User userEntity = UserMapper.mapToUser(user);
        userEntity.setRegisteredDate(java.time.LocalDate.now());
        User registeredUser = userRepo.save(userEntity);
        UserResponseDto response = UserMapper.mapToResponse(registeredUser);
        return ResponseEntity.ok(response);
    }



}
