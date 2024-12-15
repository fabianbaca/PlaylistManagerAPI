package com.fabian.PlaylistManagerAPI.controllers;

import com.fabian.PlaylistManagerAPI.model.entities.User;
import com.fabian.PlaylistManagerAPI.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void login_ShouldReturnToken_WhenCredentialsAreValid() throws Exception {
        String username = "admin";
        String password = "admin123";

        mockMvc.perform(post("/auth/login")
                .param("username", username)
                .param("password", password)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    public void login_ShouldReturnUnauthorized_WhenCredentialsAreInvalid() throws Exception {
        String username = "testuser";
        String password = "wrongpassword";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new RuntimeException("Bad credentials"));

        mockMvc.perform(post("/auth/login")
                .param("username", username)
                .param("password", password)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string("Credenciales inválidas"));
    }

    @Test
    public void register_ShouldReturnOk_WhenUserIsSuccessfullyRegistered() throws Exception {
        String username = "newuser";
        String password = "password123";
        String role = "USER";

        mockMvc.perform(post("/auth/register")
                .param("username", username)
                .param("password", password)
                .param("role", role)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Usuario registrado exitosamente"));
    }

    @Test
    public void register_ShouldReturnBadRequest_WhenUserAlreadyExists() throws Exception {
        String username = "existinguser";
        String pass = "password123";

        User user = new User();
        user.setUsername(username);
        user.setPassword(pass);
        user.setRole("USER");
        userRepository.save(user);

        mockMvc.perform(post("/auth/register")
                .param("username", username)
                .param("password", pass)
                .param("role", "USER")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("El usuario ya existe"));
    }
}
