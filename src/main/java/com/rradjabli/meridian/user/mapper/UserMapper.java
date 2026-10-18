package com.rradjabli.meridian.user.mapper;

import com.rradjabli.meridian.common.GlobalMapper;
import com.rradjabli.meridian.user.dto.UserRequestDto;
import com.rradjabli.meridian.user.dto.UserResponseDto;
import com.rradjabli.meridian.user.entity.User;

public class UserMapper {

    public static User mapToUser(UserRequestDto user){
        return GlobalMapper.map(user, new User());

    }

    public static UserResponseDto mapToResponse(User user){
        return GlobalMapper.map(user, new UserResponseDto());
    }

}
