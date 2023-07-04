package com.springwebapp.canvas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springwebapp.canvas.dto.LoginRequest;
import com.springwebapp.canvas.model.Role;
import com.springwebapp.canvas.model.User;
import com.springwebapp.canvas.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        try {
            // Perform authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            // Set the authentication details
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Return a success message
            return ResponseEntity.ok("Login successful!");
        } catch (AuthenticationException e) {
            System.err.println(e);
            // Return an error message for failed authentication
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/signup")
    public ResponseEntity<String> signup() {
        var user = User.builder()
                .username("saishanmukh")
                .password("admin@123")
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return ResponseEntity.ok("Signup successful!");
    }

}
