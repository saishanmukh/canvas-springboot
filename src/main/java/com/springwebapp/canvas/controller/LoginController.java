package com.springwebapp.canvas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springwebapp.canvas.model.User;
import com.springwebapp.canvas.service.UserService;

@RestController
@Component
public class LoginController {

    @Autowired
    private UserService userService;

    // @Autowired
    // public LoginController(UserService userService) {
    //     this.userService = userService;
    // }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

}
