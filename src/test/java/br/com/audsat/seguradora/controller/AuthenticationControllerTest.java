package br.com.audsat.seguradora.controller;

import br.com.audsat.seguradora.controller.request.AuthenticationRequest;
import br.com.audsat.seguradora.controller.request.RegisterRequest;
import br.com.audsat.seguradora.controller.response.LoginResponse;
import br.com.audsat.seguradora.core.security.TokenService;
import br.com.audsat.seguradora.model.User;
import br.com.audsat.seguradora.model.enums.UserRole;
import br.com.audsat.seguradora.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserService userService;

    @MockBean
    private TokenService tokenService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    public void testLogin() throws Exception {
        String username = "username";
        String password = "password";
        String token = "dummy_token"; // Simulando um token válido

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);

        given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).willReturn(Mockito.mock(Authentication.class));
        given(tokenService.generateToken(any(User.class))).willReturn(token); // Retornando um token válido

        testRegister();
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authenticationRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegister() throws Exception {
        String username = "username";
        String password = "password";
        UserRole role = UserRole.USER;

        RegisterRequest registerRequest = new RegisterRequest(username, password, role);

        given(userService.findByLogin(username)).willReturn(null);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerRequest)))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
