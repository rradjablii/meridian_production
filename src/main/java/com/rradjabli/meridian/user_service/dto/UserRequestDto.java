package com.rradjabli.meridian.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {

    @NotNull
    public String name;

    @NotNull
    public String surname;

    @NotNull
    @Email
    public String email;

    @NotNull
    @Size(min = 8)
    public String password;

    @NotNull
    public String phone;

}
