package com.immersia.immersiabackend.test;

import com.immersia.immersiabackend.controller.UserController;
import com.immersia.immersiabackend.dto.LoginRequest;
import com.immersia.immersiabackend.dto.RegisterRequest;
import com.immersia.immersiabackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(new UserController(userService))
                .build();
    }

    @Test
    void shouldRegisterUser() throws Exception {

        String json = """
                {
                    "username": "testuser",
                    "email": "test@gmail.com",
                    "password": "123456"
                }
                """;

        mockMvc.perform(
                        post("/api/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Kayıt başarılı"));

        verify(userService).register(any(RegisterRequest.class));
    }

    @Test
    void shouldLoginUser() throws Exception {

        String json = """
                {
                    "email": "test@gmail.com",
                    "password": "123456"
                }
                """;

        when(userService.login(any(LoginRequest.class)))
                .thenReturn("test-jwt-token");

        mockMvc.perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("test-jwt-token"));

        verify(userService).login(any(LoginRequest.class));
    }

    @Test
    void shouldReturnJwtTestMessage() throws Exception {

        mockMvc.perform(
                        get("/api/auth/test")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("JWT çalışıyor"));
    }
}