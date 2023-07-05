package com.springwebapp.canvas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.springwebapp.canvas.dto.AuthResponseDto;
import com.springwebapp.canvas.dto.LoginRequestDto;
import com.springwebapp.canvas.dto.signupRequestDto;
import com.springwebapp.canvas.error.UserError;
import com.springwebapp.canvas.model.User;
import com.springwebapp.canvas.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponseDto login(LoginRequestDto loginRequest) throws UserError {
        try {
            // Perform authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            // Set the authentication details
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Return a success message
            var jwt = "Login in Successfull"; // Need to send JWT Token
            return AuthResponseDto.builder()
                    .token(jwt)
                    .build();
        } catch (AuthenticationException e) {
            System.err.println(e);
            // Return an error message for failed authentication
            throw new UserError(UserError.ErrorType.USER_NOT_FOUND, "Invalid username or password");
        }
    }

    public AuthResponseDto signup(signupRequestDto signupRequest) throws UserError {
        try {

            var user = User.builder()
                    .username(signupRequest.getUsername())
                    .password(signupRequest.getPassword())
                    .role(signupRequest.getRole())
                    .build();
            userRepository.save(user);

            // Perform authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signupRequest.getUsername(), signupRequest.getPassword()));

            // Set the authentication details
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Return a success message
            var jwt = "User Registered Successfully"; // Need to send JWT Token
            return AuthResponseDto.builder()
                    .token(jwt)
                    .build();
        } catch (AuthenticationException e) {
            System.err.println(e);
            // Return an error message for failed authentication
            throw new UserError(UserError.ErrorType.USER_ALREADY_EXISTS, "User already exists");
        }
    }
}
