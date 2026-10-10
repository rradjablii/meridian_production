package com.rradjabli.meridian.user_service.repo;

import com.rradjabli.meridian.user_service.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
}
