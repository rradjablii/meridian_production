package com.rradjabli.meridian.company.service;

import com.rradjabli.meridian.audit.dto.AuditRequestDTO;
import com.rradjabli.meridian.audit.service.AuditService;
import com.rradjabli.meridian.common.GlobalMapper;
import com.rradjabli.meridian.common.enums.RequestState;
import com.rradjabli.meridian.common.enums.RequestType;
import com.rradjabli.meridian.company.dto.CompanyDetailsDTO;
import com.rradjabli.meridian.company.dto.CompanyResponseDTO;
import com.rradjabli.meridian.company.entity.Company;
import com.rradjabli.meridian.company.repo.CompanyRepo;
import com.rradjabli.meridian.exception.CompanyAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepo companyRepo;
    private final AuditService auditService;

    public CompanyService(CompanyRepo companyRepo, AuditService auditService) {
        this.companyRepo = companyRepo;
        this.auditService = auditService;
    }

    public CompanyResponseDTO registerNewCompany(CompanyDetailsDTO companyDetailsDTO, UUID representativeId){
        Company company = GlobalMapper.map(companyDetailsDTO, new Company());
        AuditRequestDTO auditRequestDTO = new AuditRequestDTO(company.getId(), RequestType.REGISTRATION, RequestState.STARTED);
        auditService.saveAuditLogs(auditRequestDTO);
        if(companyRepo.existsByCompanyTin(company.getCompanyTin())){
            auditRequestDTO.setRequestState(RequestState.FAILED);
            auditService.saveAuditLogs(auditRequestDTO);
            throw new CompanyAlreadyExistsException("Company already exists");
        }
        company.setRepresentativeUserIdentifier(representativeId);
        company.setRegisteredDate(Instant.now());
        companyRepo.save(company);
        auditRequestDTO.setRequestState(RequestState.COMPLETED);
        auditService.saveAuditLogs(auditRequestDTO);

        return GlobalMapper.map(company, new CompanyResponseDTO());

    }

}
