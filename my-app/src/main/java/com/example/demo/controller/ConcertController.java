package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entityFile.Events.Concert;
import com.example.demo.repository.*;

@RestController
@RequestMapping("/api/concert")
public class ConcertController {
    @Autowired //automatically inject an instance of customer repository
    private ConcertRepository concertRepository;

    @PostMapping // works
    public ResponseEntity<?> createConcert(@RequestBody Concert concert) {
        Concert savedConcert = this.concertRepository.save(concert);
        return ResponseEntity.ok(savedConcert);
    }
    @PutMapping("/{eventID}") 
    public ResponseEntity<?> setConcert(@RequestBody Concert concert, @PathVariable("eventID") Long eventID) {
        if (!this.concertRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        Concert existingConcert = this.concertRepository.findById(eventID).get();
        existingConcert.setEventName(concert.getEventName());
        existingConcert.setEventVenue(concert.getEventVenue());
        existingConcert.setEventDate(concert.getEventDate());
        existingConcert.setEventTime(concert.getEventTime());
        existingConcert.setCancellationFee(concert.getCancellationFee());
        existingConcert.setPerformers(concert.getPerformers());
        this.concertRepository.save(existingConcert);
        return ResponseEntity.ok(existingConcert);
    }
    @GetMapping 
    public List<Concert> getAllConcerts() {
        return this.concertRepository.findAll();
    }

    // get event by eventID
    @GetMapping("/{eventID}")
    public ResponseEntity<?> getConcertById(@PathVariable(value = "eventID") Long eventID) {
        if (!this.concertRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        Concert existingConcert = this.concertRepository.findById(eventID).get();
        return ResponseEntity.ok(existingConcert);
    }
}
