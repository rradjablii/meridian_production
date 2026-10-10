package com.rradjabli.meridian.user_service.exception;


public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message){
        super(message);
    }

}
