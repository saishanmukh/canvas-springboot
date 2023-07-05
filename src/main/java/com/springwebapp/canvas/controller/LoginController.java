package com.springwebapp.canvas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springwebapp.canvas.dto.LoginRequestDto;
import com.springwebapp.canvas.dto.signupRequestDto;
import com.springwebapp.canvas.error.UserError;
import com.springwebapp.canvas.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        try{
            return ResponseEntity.ok(authService.login(loginRequest));
        }
        catch (UserError e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody signupRequestDto signupRequest) {
        try{
            return ResponseEntity.ok(authService.signup(signupRequest));
        }
        catch (UserError e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
