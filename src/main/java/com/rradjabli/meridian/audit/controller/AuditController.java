package com.rradjabli.meridian.audit.controller;

import com.rradjabli.meridian.audit.dto.AuditRequestDTO;
import com.rradjabli.meridian.audit.dto.AuditResponseDTO;
import com.rradjabli.meridian.audit.service.AuditService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/audits")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @RequestMapping("/save")
    public ResponseEntity<AuditResponseDTO> saveAuditLogs(@RequestBody @Valid AuditRequestDTO auditRequestDTO){

        AuditResponseDTO auditResponseDTO = auditService.saveAuditLogs(auditRequestDTO);

        return ResponseEntity.status(201).body(auditResponseDTO);

    }

}
