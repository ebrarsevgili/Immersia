package com.immersia.immersiabackend.test;

import com.immersia.immersiabackend.model.User;
import com.immersia.immersiabackend.repository.UserRepository;
import com.immersia.immersiabackend.service.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @BeforeEach
    void setUp(){
        userRepository.deleteAll();;
        User user = new User (
                "testuser",
                "test@gmail.com",
                passwordEncoder.encode("123456")
        );
        userRepository.save(user);
    }
    @Test
    void shouldRejectWithoutJwt() throws Exception{
        mockMvc.perform(
                get("/api/auth/test")
        )
                .andExpect(status().isForbidden());
    }
    @Test
    void shouldRejectRequestWithInvalidJwt() throws Exception{
        mockMvc.perform(
                get("/api/auth/test")
                        .header("Authorization" , "Bearer shate-token")
        )
                .andExpect(status().isForbidden());
    }
    @Test
    void shouldAllowRequestWithValidJwt() throws Exception{
        String token = jwtService.generateToken("test@gmail.com");
        mockMvc.perform(
                get("/api/auth/test")
                        .header("Authorization" , "Bearer " + token)
        )
                .andExpect(status().isOk());
    }
     @Test
    void shouldLoginAndAccessProtectedEndpoint()  throws  Exception{
         String json = """
            {
                "email": "test@gmail.com",
                "password": "123456"
            }
            """;

         String token = mockMvc.perform(
                         post("/api/auth/login")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content(json)
                 )
                 .andExpect(status().isOk())
                 .andReturn()
                 .getResponse()
                 .getContentAsString();

         mockMvc.perform(
                         get("/api/auth/test")
                                 .header("Authorization", "Bearer " + token)
                 )
                 .andExpect(status().isOk())
                 .andExpect(content().string("JWT çalışıyor"));
     }

}
