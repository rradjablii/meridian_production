package com.rradjabli.meridian.audit.service;

import com.rradjabli.meridian.audit.dto.AuditRequestDTO;
import com.rradjabli.meridian.audit.dto.AuditResponseDTO;
import com.rradjabli.meridian.audit.entity.Audit;
import com.rradjabli.meridian.audit.repo.AuditRepo;
import com.rradjabli.meridian.common.GlobalMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuditService {

    private final AuditRepo auditRepo;

    public AuditService(AuditRepo auditRepo) {
        this.auditRepo = auditRepo;
    }

    public AuditResponseDTO saveAuditLogs(AuditRequestDTO auditRequestDTO){

        if(auditRepo.existsByRequestId(auditRequestDTO.getRequestId())) {
            auditRequestDTO.setAttemptNumber(auditRequestDTO.getAttemptNumber() + 1);
        }

        try{
            auditRequestDTO.setAttemptId(UUID.randomUUID());

            Audit audit = GlobalMapper.map(auditRequestDTO, new Audit());

            auditRepo.save(audit);

            return GlobalMapper.map(auditRepo.save(audit), new AuditResponseDTO());

        } catch (RuntimeException e) {
            throw new RuntimeException("unknown error occurred during saving audit logs");
        }


    }

}
