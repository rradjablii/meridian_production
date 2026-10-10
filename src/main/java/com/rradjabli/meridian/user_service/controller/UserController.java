package com.rradjabli.meridian.user_service.controller;

import com.rradjabli.meridian.user_service.dto.UserRequestDto;
import com.rradjabli.meridian.user_service.dto.UserResponseDto;
import com.rradjabli.meridian.user_service.entitiy.User;
import com.rradjabli.meridian.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user){
        return userService.registerNewUser(user);
    }

}
