package com.rradjabli.meridian.audit.repo;

import com.rradjabli.meridian.audit.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditRepo extends JpaRepository<Audit, UUID> {

    boolean existsByRequestId(UUID requestId);

}
