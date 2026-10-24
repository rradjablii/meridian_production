package com.rradjabli.meridian.company.service;

import com.rradjabli.meridian.common.GlobalMapper;
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

    public CompanyService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public CompanyResponseDTO registerNewCompany(CompanyDetailsDTO companyDetailsDTO, UUID representativeId){
        Company company = GlobalMapper.map(companyDetailsDTO, new Company());
        if(companyRepo.existsByCompanyTin(company.getCompanyTin())){
            throw new CompanyAlreadyExistsException("Company already exists");
        }
        company.setRepresentativeUserIdentifier(representativeId);
        company.setRegisteredDate(Instant.now());
        companyRepo.save(company);

        return GlobalMapper.map(company, new CompanyResponseDTO());

    }

}
