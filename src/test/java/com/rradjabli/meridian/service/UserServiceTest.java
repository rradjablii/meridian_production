package com.rradjabli.meridian.service;

import com.rradjabli.meridian.user.dto.UserRequestDto;
import com.rradjabli.meridian.user.dto.UserResponseDto;
import com.rradjabli.meridian.user.entity.User;
import com.rradjabli.meridian.exception.UserAlreadyExistsException;
import com.rradjabli.meridian.user.repo.UserRepo;
import com.rradjabli.meridian.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserRequestDto userRequestDto;


    @BeforeEach
    void setUp() {
        userRequestDto = new UserRequestDto(
                "Ruslan",
                "Rajabli",
                "ruslan@example.com",
                "Password1234",
                "0773934747"
        );
    }

    @Test
    void registerUser_saveUserAndReturnUserResponseDto() {

        when(userRepo.existsByEmail(userRequestDto.getEmail())).thenReturn(false);

        when(passwordEncoder.encode(userRequestDto.getPassword())).thenReturn("encodedPassword");

        when(userRepo.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));


        //test
        UserResponseDto userResponseDto = userService.registerNewUser(userRequestDto);

        assertNotNull(userResponseDto);

        verify(userRepo).save(argThat(user -> user.getEmail().equals(userRequestDto.getEmail())
        && user.getPassword().equals("encodedPassword")));

    }

    @Test
    void registerUser_throwExceptionIfUserAlreadyExists(){

        when(userRepo.existsByEmail(userRequestDto.getEmail())).thenReturn(true);

        assertThrows(
                UserAlreadyExistsException.class,
                ()-> userService.registerNewUser(userRequestDto)
        );

        verify(userRepo, never()).save(any(User.class));

    }

    @Test
    void registerUser_shouldEncodePasswordBeforeSaving(){

        when(userRepo.existsByEmail(userRequestDto.getEmail())).thenReturn(false);

        when(passwordEncoder.encode(userRequestDto.getPassword())).thenReturn("encodedPassword");

        when(userRepo.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        userService.registerNewUser(userRequestDto);

        verify(passwordEncoder).encode(userRequestDto.getPassword());

        verify(userRepo).save(argThat(user -> user.getPassword().equals("encodedPassword")));

    }

}
