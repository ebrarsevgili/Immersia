package com.immersia.immersiabackend.service;
import com.immersia.immersiabackend.dto.LoginRequest;
import com.immersia.immersiabackend.dto.RegisterRequest;
import com.immersia.immersiabackend.model.User;
import com.immersia.immersiabackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    public UserService(
            UserRepository userRepository ,
            PasswordEncoder passwordEncoder ,
            JWTService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Bu email zaten kayıtlı");
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);
    }
    public  String  login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı"));
        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )){
            throw new RuntimeException("Şifre hatalı");
        }
        return jwtService.generateToken(user.getEmail());
    }
    public User getUserByEmail(String  email){
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı"));
    }
}