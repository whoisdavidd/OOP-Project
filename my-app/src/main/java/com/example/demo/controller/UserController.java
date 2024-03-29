package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entityFile.Users.User;
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
    public ResponseEntity<?> getUserByUsername(@PathVariable(value = "username") String username) {
        if (!this.userRepository.findById(username).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        User existingUser = this.userRepository.findById(username).get();
        return ResponseEntity.ok(existingUser);
    }

    // create user
    // @PostMapping
    // public User createUser(@RequestBody User user) {
    //     boolean existingUser = this.userRepository.existsById(user.getUsername());
    //     if (existingUser){
    //         throw new IllegalArgumentException("The user already exists.");
    //     }else{
    //         User savedUser = this.userRepository.save(user);
    //         return savedUser;
    //     }
        
    // }

    // update user
    // @PutMapping("/{username}")
    // public User updateUser(@RequestBody User user, @PathVariable("username") String username) {
    //     User existingUser = this.userRepository.findById(username)
    //             .orElseThrow(() -> new ResourceNotFoundException("User not found with username :" + username));
    //     existingUser.setPassword(user.getPassword());

    //     return this.userRepository.save(existingUser);
    // }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        if (!this.userRepository.findById(username).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        User existingUser = this.userRepository.findById(username).get();
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok("User has been deleted");
    }
}