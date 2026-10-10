package com.rradjabli.meridian.user_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserResponseDto {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private LocalDate registeredDate;

}
