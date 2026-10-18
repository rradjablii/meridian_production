package com.rradjabli.meridian.company.dto;

import com.rradjabli.meridian.user.dto.UserRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequestDTO {

    @NotNull
    @Valid
    private CompanyDetailsDTO companyDetailsDTO;

    @NotNull
    @Valid
    private UserRequestDto userRequestDTO;

}
