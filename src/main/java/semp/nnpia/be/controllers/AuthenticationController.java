package semp.nnpia.be.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import semp.nnpia.be.domains.Address;
import semp.nnpia.be.domains.State;
import semp.nnpia.be.domains.User;
import semp.nnpia.be.dtos.AddressInputDto;
import semp.nnpia.be.dtos.UserInputDto;
import semp.nnpia.be.services.*;
import semp.nnpia.be.utils.JwtTokenUtil;
import semp.nnpia.be.utils.StateUtils;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    AddressService addressService;
    @Autowired
    StateService stateService;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Validated @RequestBody Map<String, Object> payload) {
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            if (auth.isAuthenticated()) {
                logger.info("Logged In.");
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
                String token = jwtTokenUtil.generateToken(userDetails);
                responseMap.put("error", false);
                responseMap.put("message", "Logged In.");
                responseMap.put("token", token);
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("message", "Invalid Credentials.");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "User is disabled.");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Invalid Credentials.");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong.");
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody final UserInputDto dto) throws ResourceNotFoundException {
        User existingUser = userService.getUserByUsername(dto.getUsername());

        if (existingUser != null) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("error", true);
            responseMap.put("message", "User already exists.");
            return ResponseEntity.status(409).body(responseMap);
        }

        var user = toEntity(dto);

        State existingState = stateService.getStateByShortcut(user.getAddress().getState().getShortcut());

        if (existingState == null) {
            stateService.createState(user.getAddress().getState());
        } else {
            user.getAddress().setState(existingState);
        }

        Address existingAddress = addressService.getExactMatchAddress(user.getAddress());

        if (existingAddress == null) {
            addressService.createAddress(user.getAddress());
        } else {
            user.setAddress(existingAddress);
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.createUser(user);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("error", false);
        responseMap.put("username", user.getUsername());
        responseMap.put("message", "Account created successfully.");
        responseMap.put("token", token);
        return ResponseEntity.ok(responseMap);
    }

    @GetMapping("/username")
    public Map<String, Object> getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("error", false);
        userMap.put("username", authentication.getName());
        return userMap;
    }

    @GetMapping("/password/{password}")
    public ResponseEntity<?> password(@PathVariable String password) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("original", password);
        responseMap.put("password", new BCryptPasswordEncoder().encode(password));
        return ResponseEntity.ok(responseMap);
    }

    private User toEntity(final UserInputDto dto) {
        return new User(
                dto.getUsername(),
                dto.getPassword(),
                dto.getName(),
                dto.getSurname(),
                dto.getDateOfBirth(),
                dto.getPhone(),
                dto.getEmail(),
                toEntity(dto.getAddress()),
                dto.getCreatedAt()
        );
    }

    private Address toEntity(final AddressInputDto dto) {
        return new Address(
                dto.getStreetAddress(),
                dto.getCity(),
                dto.getZipCode(),
                toEntity(dto.getState())
        );
    }

    private State toEntity(final String state) {
        return new State(
                state,
                StateUtils.getStateShortcut(state)
        );
    }
}