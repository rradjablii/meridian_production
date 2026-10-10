package com.rradjabli.meridian.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {

    @NotBlank
    public String name;

    @NotBlank
    public String surname;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    @Size(min = 8)
    public String password;

    @NotBlank
    public String phone;

}
