
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.example.demo.entityFile.Ticketing.TicketingOption;
import com.example.demo.repository.*;

@RestController
@RequestMapping("/api/ticketingOption")
public class TicketingOptionController {
    @Autowired //automatically inject an instance of customer repository
    private TicketingOptionRepository ticketingOptionRepository;

    @Autowired
    private EventRepository eventRepository;
    
    @PostMapping("/{id}") // works
    public ResponseEntity<?> createTicketingOption(@RequestBody TicketingOption ticketingOption, @PathVariable("id") long eventID) {
        if (!this.eventRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        ticketingOption.setEvent(this.eventRepository.findById(eventID).get());
        TicketingOption savedTicketingOption = this.ticketingOptionRepository.save(ticketingOption);
        return ResponseEntity.ok(savedTicketingOption);
    }

    @PutMapping("/{toID}") // works
    public ResponseEntity<?> updateTicketingOption(@RequestBody TicketingOption TicketingOption, @PathVariable("toID") long toID) {
        if (!this.ticketingOptionRepository.findById(toID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticketing option not found");
        }
        TicketingOption existingTicketingOption = this.ticketingOptionRepository.findById(toID).get();
        existingTicketingOption.setTierPrice(TicketingOption.getTierPrice());
        existingTicketingOption.setEvent(TicketingOption.getEvent());
        existingTicketingOption.setTierQuantity(TicketingOption.getTierQuantity());
        this.ticketingOptionRepository.save(existingTicketingOption);
        return ResponseEntity.ok(existingTicketingOption);
    }

    @GetMapping //works
    public List<TicketingOption> getAllTicketingOptions() {
        return this.ticketingOptionRepository.findAll();
    }

    @GetMapping("/{toID}") //works
    public ResponseEntity<?> getTicketingOptionById(@PathVariable(value = "toID") Long toID) {
        if (!this.ticketingOptionRepository.findById(toID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticketing Option not found");
        }
        TicketingOption existingTicketingOption = this.ticketingOptionRepository.findById(toID).get();
        return ResponseEntity.ok(existingTicketingOption);
    }

    @DeleteMapping("/{toID}") // might need to consider the tickets and refund idk
    public ResponseEntity<?> deleteEvent(@PathVariable(value = "toID") long toID) {
        if (!this.eventRepository.findById(toID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticketing option not found");
        }
        TicketingOption existingTicketingOption = this.ticketingOptionRepository.findById(toID).get();
        this.ticketingOptionRepository.delete(existingTicketingOption);
        return ResponseEntity.ok("Ticketing option has been deleted");

    }
}
