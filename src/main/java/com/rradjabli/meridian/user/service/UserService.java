package com.rradjabli.meridian.user.service;

import com.rradjabli.meridian.audit.dto.AuditRequestDTO;
import com.rradjabli.meridian.audit.service.AuditService;
import com.rradjabli.meridian.common.enums.RequestState;
import com.rradjabli.meridian.common.enums.RequestType;
import com.rradjabli.meridian.user.dto.UserRequestDto;
import com.rradjabli.meridian.user.dto.UserResponseDto;
import com.rradjabli.meridian.user.entity.User;
import com.rradjabli.meridian.exception.UserAlreadyExistsException;
import com.rradjabli.meridian.user.mapper.UserMapper;
import com.rradjabli.meridian.user.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuditService auditService;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, AuditService auditService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.auditService = auditService;
    }

    public UserResponseDto registerNewUser(UserRequestDto user){

        User userEntity = UserMapper.mapToUser(user);
        userEntity.setEmail(userEntity.getEmail().trim().toLowerCase());

        AuditRequestDTO auditRequestDTO = new AuditRequestDTO(userEntity.getId(), RequestType.REGISTRATION, RequestState.STARTED);
        auditService.saveAuditLogs(auditRequestDTO);
        if(userRepo.existsByEmail(userEntity.getEmail())){
            auditRequestDTO.setRequestState(RequestState.FAILED);
            auditService.saveAuditLogs(auditRequestDTO);
            throw new UserAlreadyExistsException("User already exists");
        }

        userEntity.setRegisteredDate(Instant.now());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepo.save(userEntity);
        auditRequestDTO.setRequestState(RequestState.COMPLETED);
        auditService.saveAuditLogs(auditRequestDTO);

        return UserMapper.mapToResponse(userEntity);
    }

}
