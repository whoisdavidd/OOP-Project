package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entityFile.Users.User;
import com.example.demo.repository.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000") 
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
    public ResponseEntity<?> getUserByUsername(@PathVariable(value = "username") String username) {
        if (!this.userRepository.findByUsername(username).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        User existingUser = this.userRepository.findByUsername(username).get();
        return ResponseEntity.ok(existingUser);
    }
    
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        if (!this.userRepository.findById(username).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        User existingUser = this.userRepository.findById(username).get();
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok("User has been deleted");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        // Handle login logic here, e.g., check username and password against database
        Optional<User> existingUserOptional = userRepository.findByUsername(user.getUsername());

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            if (user.getPassword().equals(existingUser.getPassword())) {
                // Login successful
                return ResponseEntity.ok().body(existingUser);
            }
        }

        // Username not found or password does not match
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

}