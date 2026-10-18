package com.rradjabli.meridian.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
public class CompanyResponseDTO {

    private UUID companyId;
    private String companyName;
    private String companyTin;
    private String companyAddress;
    private String companyPhone;
    private Instant registeredDate;
    private String representativeUserIdentifier;

}
