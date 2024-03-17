package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.example.demo.entityFile.Users.TicketingOfficer;
import com.example.demo.repository.*;
import com.example.demo.services.UserService;


@RestController
@RequestMapping("/api/ticketingOfficer")
public class TicketingOfficerController {
    @Autowired //automatically inject an instance of customer repository
    private TicketingOfficerRepository ticketingOfficerRepository;
    
    @Autowired
    private UserService userService;

    @PostMapping //works
    public ResponseEntity<?> createTicketingOfficer(@RequestBody TicketingOfficer TicketingOfficer) {
        if (!userService.isUsernameValid(TicketingOfficer.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken or is invalid");
        }
        TicketingOfficer savedTicketingOfficer = this.ticketingOfficerRepository.save(TicketingOfficer);
        return ResponseEntity.ok(savedTicketingOfficer);
    }

    @PutMapping("/{username}") // works
    public ResponseEntity<?> updateTicketingOfficer(@RequestBody TicketingOfficer TicketingOfficer, @PathVariable("username") String username) {
        if (!this.ticketingOfficerRepository.findById(username).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        TicketingOfficer existingUser = this.ticketingOfficerRepository.findById(username).get();
        existingUser.setPassword(TicketingOfficer.getPassword());
        this.ticketingOfficerRepository.save(existingUser);
        return ResponseEntity.ok(existingUser);
    }

    @GetMapping //works
    public List<TicketingOfficer> getAllTicketingOfficers() {
        return this.ticketingOfficerRepository.findAll();
    }

    // get user by username
    @GetMapping("/{username}") //works
    public ResponseEntity<?> getTicketingOfficerById(@PathVariable(value = "username") String username) {
        if (!this.ticketingOfficerRepository.findById(username).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        TicketingOfficer existingUser = this.ticketingOfficerRepository.findById(username).get();
        return ResponseEntity.ok(existingUser);
    }
}
