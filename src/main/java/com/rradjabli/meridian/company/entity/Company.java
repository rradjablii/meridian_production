package com.rradjabli.meridian.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companies")
public class Company {

    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "company_name", unique = true, nullable = false)
    private String companyName;
    @Column(name = "company_tin", nullable = false, unique = true)
    private String companyTin;//10 digits
    @Column(name = "company_address", nullable = false)
    private String companyAddress;
    @Column(name = "company_phone", nullable = false, unique = true, length = 16)
    private String companyPhone;
    @Column(name = "registered_date", nullable = false)
    private Instant registeredDate;

    @Column(name = "representative_id", nullable = false, unique = true)
    private UUID representativeUserIdentifier;


}
