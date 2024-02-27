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

import com.example.demo.entityFile.*;
import com.example.demo.entityFile.Users.Customer;
import com.example.demo.entityFile.Users.User;
import com.example.demo.exception.*;
import com.example.demo.repository.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    // get all users
    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
    }

    // create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        User savedUser = this.userRepository.save(user);
        Customer customer = new Customer();
        customer.setId(savedUser.getId());
        customer.setUsername(savedUser.getUsername());
        customer.setPassword(savedUser.getPassword());
        this.customerRepository.save(customer);
        return savedUser;
    }

    // update user
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());

        return this.userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}