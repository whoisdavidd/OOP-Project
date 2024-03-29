package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entityFile.Events.SportsEvent;
import com.example.demo.repository.*;

@RestController
@RequestMapping("/api/SportsEvent")
@CrossOrigin(origins = "http://localhost:8081") 
public class SportsEventController {
    @Autowired //automatically inject an instance of customer repository
    private SportsEventRepository sportsEventRepository;

    @PostMapping // works
    public ResponseEntity<?> createSportsEvent(@RequestBody SportsEvent SportsEvent) {
        SportsEvent savedSportsEvent = this.sportsEventRepository.save(SportsEvent);
        return ResponseEntity.ok(savedSportsEvent);
    }
    @PutMapping("/{eventID}") 
    public ResponseEntity<?> setSportsEvent(@RequestBody SportsEvent sportsEvent, @PathVariable("eventID") Long eventID) {
        if (!this.sportsEventRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        SportsEvent existingSportsEvent = this.sportsEventRepository.findById(eventID).get();
        existingSportsEvent.setEventName(sportsEvent.getEventName());
        existingSportsEvent.setEventVenue(sportsEvent.getEventVenue());
        existingSportsEvent.setEventDate(sportsEvent.getEventDate());
        existingSportsEvent.setEventTime(sportsEvent.getEventTime());
        existingSportsEvent.setCancellationFee(sportsEvent.getCancellationFee());
        existingSportsEvent.setParticipants(sportsEvent.getParticipants());
        existingSportsEvent.setSport(sportsEvent.getSport());
        this.sportsEventRepository.save(existingSportsEvent);
        return ResponseEntity.ok(existingSportsEvent);
    }
    @GetMapping 
    public List<SportsEvent> getAllSportsEvents() {
        return this.sportsEventRepository.findAll();
    }

    // get event by eventID
    @GetMapping("/{eventID}")
    public ResponseEntity<?> getSportsEventById(@PathVariable(value = "eventID") Long eventID) {
        if (!this.sportsEventRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        SportsEvent existingSportsEvent = this.sportsEventRepository.findById(eventID).get();
        return ResponseEntity.ok(existingSportsEvent);
    }
}
