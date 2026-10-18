package com.rradjabli.meridian.company.repo;

import com.rradjabli.meridian.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepo extends JpaRepository<Company, UUID> {

    boolean existsByCompanyTin(String companyTin);
    
}
