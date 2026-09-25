package com.immersia.immersiabackend.test;

import com.immersia.immersiabackend.service.JWTService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class JWTServiceTest {
    private JWTService jwtService;

    @BeforeEach
    void setUp(){
        jwtService = new JWTService(
                "this-is-a-test-secret-key-that-is-long-enough"
        );
    }
    @Test
    void shouldGenerateToken(){
        String email = "test@gmail.com";
        String  token = jwtService.generateToken(email);
        Assertions.assertNotNull(token);

    }
    @Test
    void shouldExtractEmailFromToken(){
        String email = "test@gmail.com";
        String token = jwtService.generateToken(email);
        String extractedEmail = jwtService.extractEmail(token);
        Assertions.assertEquals(email ,extractedEmail);
    }
    @Test
    void shouldReturnTrueForValidToken(){
        String token = jwtService.generateToken("test@gmail.com");
        boolean result = jwtService.isTokenValid(token);
        Assertions.assertEquals(true, result);
    }
    @Test
    void shouldReturnFalseForValidToken(){
        String token = "invalid-token";
        boolean result = jwtService.isTokenValid(token);
        Assertions.assertFalse(result);
    }
}
