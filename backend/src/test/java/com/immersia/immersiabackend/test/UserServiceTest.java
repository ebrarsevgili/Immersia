package com.immersia.immersiabackend.test;

import com.immersia.immersiabackend.dto.LoginRequest;
import com.immersia.immersiabackend.dto.RegisterRequest;
import com.immersia.immersiabackend.model.User;
import com.immersia.immersiabackend.repository.UserRepository;
import com.immersia.immersiabackend.service.JWTService;
import com.immersia.immersiabackend.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JWTService jwtService;
    @InjectMocks
    private UserService userService;

    @Test
    void shouldRegisterNewUser(){
        RegisterRequest request = new RegisterRequest(
                "testuser",
                "test@gmail.com",
                "123456"
        );
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode("123456"))
                .thenReturn("encodePassword");
        userService.register(request);
        Mockito.verify(userRepository).save(ArgumentMatchers.any(User.class));
    }
    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists(){
        RegisterRequest request = new RegisterRequest(
                "testuser",
                "test@gmail.com",
                "123456"
        );
        User existingUser = new User(
                "olduser",
                "test@gmail.com",
                "oldpassword"
        );
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(existingUser));
        Assertions.assertThrows(
                RuntimeException.class,
                ()-> userService.register(request)
        );
    }
    @Test
    void shouldEncodePasswordWhenRegistering(){
        RegisterRequest request = new RegisterRequest(
                "testuser",
                "test@gmail.com",
                "123456"
        );
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode("123456"))
                .thenReturn("encodePassword");
        userService.register(request);
        Mockito.verify(passwordEncoder).encode("123456");
    }
    @Test
    void shouldLoginSuccessfully(){
        LoginRequest request = new LoginRequest(
                "test@gmail.com",
                "123456"
        );
        User user = new User(
                "testuser",
                "test@gmail.com",
                "encodedPassword"
        );

        user.setId(4L);
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches("123456" , "encodedPassword"))
                .thenReturn(true);
        Mockito.when(jwtService.generateToken("test@gmail.com"))
                .thenReturn("fake-jwt-token");
        String token = userService.login(request);
        Assertions.assertEquals("fake-jwt-token" ,token);
    }
    @Test
    void shouldThrowExceptionWhenPasswordIsWrong(){
        LoginRequest request = new LoginRequest(
                "test@gmail.com",
                "wrongPassword"
        );

        User user = new User(
                "testuser",
                "test@gmail.com",
                "encodedPassword"
        );
        user.setId(4L);
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(
                "wrongPassword",
                "encodedPassword"
        )).thenReturn(false);
        Assertions.assertThrows(
                RuntimeException.class,
                ()-> userService.login(request)
        );
    }

}
