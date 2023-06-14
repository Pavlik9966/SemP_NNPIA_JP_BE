package semp.nnpia.be.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import semp.nnpia.be.services.AddressService;
import semp.nnpia.be.services.JwtUserDetailsService;
import semp.nnpia.be.services.StateService;
import semp.nnpia.be.services.UserService;
import semp.nnpia.be.utils.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ControllerTest {
    @Mock
    private AddressService addressService;
    @Mock
    private StateService stateService;
    @Mock
    private UserService userService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @Mock
    private JwtUserDetailsService jwtUserDetailsService;
    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginUser() {
        String username = "user";
        String password = "password";
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("password", password);

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);

        UserDetails userDetails = mock(UserDetails.class);
        when(jwtUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        String token = "token";
        when(jwtTokenUtil.generateToken(userDetails)).thenReturn(token);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("error", false);
        expectedResponse.put("message", "Logged In.");
        expectedResponse.put("token", token);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        ResponseEntity<?> response = authenticationController.loginUser(payload);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());

        verify(jwtUserDetailsService).loadUserByUsername(username);
        verify(jwtTokenUtil).generateToken(userDetails);
        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Test
    public void testLoginInvalidUser() {
        String username = "user";
        String password = "password";
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("password", password);

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);

        ResponseEntity<?> expectedResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials.");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        ResponseEntity<?> response = authenticationController.loginUser(payload);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());

        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assert responseBody != null;
        assertTrue((boolean) responseBody.get("error"));
        assertEquals("Invalid Credentials.", responseBody.get("message"));

        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}