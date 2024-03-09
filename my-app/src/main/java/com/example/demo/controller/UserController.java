package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entityFile.Users.User;
import com.example.demo.exception.*;
import com.example.demo.repository.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // get all users
    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // get user by username
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable(value = "username") String username) {
        return this.userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username :" + username));
    }

    // create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        boolean existingUser = this.userRepository.existsById(user.getUsername());
        if (existingUser){
            throw new IllegalArgumentException("The user already exists.");
        }else{
            User savedUser = this.userRepository.save(user);
            return savedUser;
        }
        
    }

    // update user
    @PutMapping("/{username}")
    public User updateUser(@RequestBody User user, @PathVariable("username") String username) {
        User existingUser = this.userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username :" + username));
        existingUser.setPassword(user.getPassword());

        return this.userRepository.save(existingUser);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable("username") String username) {
        User existingUser = this.userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username :" + username));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}