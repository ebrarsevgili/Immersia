package com.immersia.immersiabackend.controller;


import com.immersia.immersiabackend.dto.LoginRequest;
import com.immersia.immersiabackend.dto.RegisterRequest;
import com.immersia.immersiabackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private static final MediaType TEXT_UTF8 = new MediaType("text", "plain", java.nio.charset.StandardCharsets.UTF_8);
    private final UserService userService;

    public UserController( UserService userService){
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(
           @Valid @RequestBody RegisterRequest request
    ){
        userService.register(request);
        return ResponseEntity.ok()
                .contentType(TEXT_UTF8)
                .body("Kayıt başarılı");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
           @Valid @RequestBody LoginRequest request
    ){
        String token = userService.login(request);
        return ResponseEntity.ok()
                .contentType(TEXT_UTF8)
                .body(token);
    }
    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok()
                .contentType(TEXT_UTF8)
                .body("JWT çalışıyor");
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(
            Authentication authentication
    ){
        String  email = authentication.getName();
        return ResponseEntity.ok(
                userService.getUserByEmail(email)
        );
    }
}
