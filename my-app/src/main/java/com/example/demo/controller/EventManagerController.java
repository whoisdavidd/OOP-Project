package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entityFile.Users.EventManager;
import com.example.demo.repository.*;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/eventManager")
public class EventManagerController {
    @Autowired //automatically inject an instance of customer repository
    private EventManagerRepository eventManagerRepository;

    @Autowired
    private UserService userService;

    @PostMapping //works
    public ResponseEntity<?> createEventManager(@RequestBody EventManager eventManager) {
        if (!userService.isUsernameValid(eventManager.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken or is invalid");
        }
        EventManager savedEventManager = this.eventManagerRepository.save(eventManager);
        return ResponseEntity.ok(savedEventManager);
    }

    @PutMapping("/{username}") // works
    public ResponseEntity<?> updateEventManager(@RequestBody EventManager eventManager, @PathVariable("username") String username) {
        if (!this.eventManagerRepository.findById(username).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        EventManager existingUser = this.eventManagerRepository.findById(username).get();
        existingUser.setPassword(eventManager.getPassword());
        this.eventManagerRepository.save(existingUser);
        return ResponseEntity.ok(existingUser);
    }

    @GetMapping //works
    public List<EventManager> getAllEventManagers() {
        return this.eventManagerRepository.findAll();
    }

    // get user by username
    @GetMapping("/{username}") //works
    public ResponseEntity<?> getEventManagerById(@PathVariable(value = "username") String username) {
        if (!this.eventManagerRepository.findById(username).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        EventManager existingUser = this.eventManagerRepository.findById(username).get();
        return ResponseEntity.ok(existingUser);
    }
}
