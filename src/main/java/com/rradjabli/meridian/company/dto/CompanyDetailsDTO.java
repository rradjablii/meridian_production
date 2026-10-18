package com.rradjabli.meridian.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDetailsDTO {

    @NotBlank
    private String companyName;
    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Enter valid TIN number")
    private String companyTin;
    @NotBlank
    private String companyAddress;
    @NotBlank
    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Phone number must be in international format e.g. +994 1231313")
    private String companyPhone;

}
