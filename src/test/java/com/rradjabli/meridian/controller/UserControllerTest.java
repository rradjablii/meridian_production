package com.rradjabli.meridian.controller;

import com.rradjabli.meridian.user.security.SecurityConfig;
import com.rradjabli.meridian.user.controller.UserController;
import com.rradjabli.meridian.user.dto.UserRequestDto;
import com.rradjabli.meridian.user.dto.UserResponseDto;
import com.rradjabli.meridian.user.service.UserService;
import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @Test
    void registerUser_shouldReturn201WhenSuccessful() throws Exception {
        UserRequestDto request = new UserRequestDto(
                "Ruslan",
                "Radjabli",
                "ruslan@example.com",
                "plainPassword123",
                "+994501234567"
        );

        // Adapt this to your actual UserResponseDto constructor.
        UserResponseDto response = new UserResponseDto(
                UUID.randomUUID(),
                "Ruslan",
                "Rajabli",
                "ruslan@example.com",
                "0773934747",
                Instant.now()
        );

        when(userService.registerNewUser(any(UserRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void registerUser_shouldReturn400WhenRequestIsInvalid() throws Exception {
        String invalidRequest = """
                {
                  "name": "",
                  "surname": "",
                  "email": "not-an-email",
                  "password": "",
                  "phone": ""
                }
                """;

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(invalidRequest))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(userService);
    }

}
