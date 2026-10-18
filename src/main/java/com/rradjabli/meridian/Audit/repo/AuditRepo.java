package com.rradjabli.meridian.Audit.repo;

import com.rradjabli.meridian.Audit.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditRepo extends JpaRepository<Audit, UUID> {

    boolean existsByRequestId(UUID requestId);

}
