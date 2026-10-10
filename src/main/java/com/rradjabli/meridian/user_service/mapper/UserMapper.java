package com.rradjabli.meridian.user_service.mapper;

import com.rradjabli.meridian.user_service.dto.UserRequestDto;
import com.rradjabli.meridian.user_service.dto.UserResponseDto;
import com.rradjabli.meridian.user_service.entitiy.User;

public class UserMapper {

    public static User mapToUser(UserRequestDto user){
        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());
        userEntity.setPassword(user.getPassword());
        return userEntity;

    }

    public static UserResponseDto mapToResponse(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setSurname(user.getSurname());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setRegisteredDate(user.getRegisteredDate());
        return userResponseDto;
    }

}
