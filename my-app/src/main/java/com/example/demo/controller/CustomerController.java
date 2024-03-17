
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entityFile.Users.Customer;
import com.example.demo.services.UserService;

import com.example.demo.repository.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired //automatically inject an instance of customer repository
    private CustomerRepository customerRepository;

    @Autowired
    private UserService userService;

    @PostMapping // works
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        if (!userService.isUsernameValid(customer.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken or is invalid");
        }
        customer.setAccountBalance(5000);
        Customer savedCustomer = this.customerRepository.save(customer);
        return ResponseEntity.ok(savedCustomer);
    }
    @PutMapping("/{username}") 
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable("username") String username) {
        if (!this.customerRepository.findById(username).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        Customer existingUser = this.customerRepository.findById(username).get();
        existingUser.setEmailAddress(customer.getEmailAddress());
        existingUser.setAccountBalance(customer.getAccountBalance());
        existingUser.setPassword(customer.getPassword());
        this.customerRepository.save(existingUser);
        return ResponseEntity.ok(existingUser);
    }
    @GetMapping 
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    // get user by username
    @GetMapping("/{username}")
    public ResponseEntity<?> getCustomerById(@PathVariable(value = "username") String username) {
        if (!this.customerRepository.findById(username).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        Customer existingUser = this.customerRepository.findById(username).get();
        return ResponseEntity.ok(existingUser);
    }
}
