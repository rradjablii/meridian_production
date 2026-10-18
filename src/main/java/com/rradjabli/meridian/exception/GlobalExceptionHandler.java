package com.rradjabli.meridian.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        return ResponseEntity.status(409).body("Email or phone number already exists");
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<String> handleCompanyAlreadyExistsException(CompanyAlreadyExistsException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

}
