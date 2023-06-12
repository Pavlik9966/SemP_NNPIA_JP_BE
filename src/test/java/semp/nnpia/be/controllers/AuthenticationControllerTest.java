package semp.nnpia.be.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import semp.nnpia.be.domains.User;
import semp.nnpia.be.dtos.UserInputDto;
import semp.nnpia.be.services.JwtUserDetailsService;
import semp.nnpia.be.services.UserService;
import semp.nnpia.be.utils.JwtTokenUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest {
    private MockMvc mockMvc;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @Mock
    private JwtUserDetailsService jwtUserDetailsService;
    @Mock
    private UserService userService;
    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    public void testLoginValidUser() throws Exception {
        String username = "validUser";
        String password = "password";

        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("password", password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of());

        String token = "validToken";

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(SecurityContextHolder.getContext().getAuthentication());
        when(jwtTokenUtil.generateToken(userDetails)).thenReturn(token);
        when(jwtUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(token));
    }

    @Test
    public void testLoginInvalidUser() throws Exception {
        String username = "invalidUser";
        String password = "password";

        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("password", password);

        when(authenticationManager.authenticate(any(Authentication.class))).thenThrow(BadCredentialsException.class);

        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(payload)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid Credentials."));
    }

    @Test
    public void testRegisterValidUser() throws Exception {
        UserInputDto dto = new UserInputDto();
        dto.setUsername("validUser");
        dto.setPassword("password");

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));

        when(userService.createUser(any(User.class))).thenReturn(user);
        when(userService.getUserByUsername(dto.getUsername())).thenReturn(null);

        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Account created successfully."));
    }

    @Test
    public void testRegisterInvalidUser() throws Exception {
        UserInputDto dto = new UserInputDto();
        dto.setUsername("invalidUser");
        dto.setPassword("password");

        User existingUser = new User();
        existingUser.setUsername(dto.getUsername());

        when(userService.getUserByUsername(dto.getUsername())).thenReturn(existingUser);

        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(dto)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("User already exists."));
    }

    private String asJsonString(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}