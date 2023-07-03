package com.springwebapp.canvas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springwebapp.canvas.model.User;
import com.springwebapp.canvas.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // public UserService(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
