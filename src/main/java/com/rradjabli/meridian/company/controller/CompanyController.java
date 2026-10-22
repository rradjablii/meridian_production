package com.rradjabli.meridian.company.controller;

import com.rradjabli.meridian.audit.service.AuditService;
import com.rradjabli.meridian.company.dto.CompanyRequestDTO;
import com.rradjabli.meridian.company.dto.CompanyResponseDTO;
import com.rradjabli.meridian.company.service.CompanyService;
import com.rradjabli.meridian.exception.CompanyAlreadyExistsException;
import com.rradjabli.meridian.exception.UserAlreadyExistsException;
import com.rradjabli.meridian.user.dto.UserResponseDto;
import com.rradjabli.meridian.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final UserService userService;
    private final AuditService auditService;

    public CompanyController(CompanyService companyService, UserService userService, AuditService auditService) {
        this.auditService = auditService;
        this.companyService = companyService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<CompanyResponseDTO> registerNewCompany(
            @RequestBody @Valid CompanyRequestDTO companyRequestDTO){

        try{
            UserResponseDto userResponseDto = userService.registerNewUser(companyRequestDTO.getUserRequestDTO());
            CompanyResponseDTO response = companyService.registerNewCompany(companyRequestDTO.getCompanyDetailsDTO(), userResponseDto.getId());
            return ResponseEntity.status(201).body(response);
        }catch (UserAlreadyExistsException e){
            throw new UserAlreadyExistsException("User already exists");
        }catch (CompanyAlreadyExistsException e){
            throw new CompanyAlreadyExistsException("Company already exists");
        }
    }

}
