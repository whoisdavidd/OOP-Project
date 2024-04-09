package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entityFile.Events.Event;
import com.example.demo.entityFile.Ticketing.TicketingOption;
import com.example.demo.repository.EventRepository;
import com.example.demo.requestBodies.SellTicketsBody;

@RestController
@RequestMapping("/api/event")
@CrossOrigin(origins = "http://localhost:8081") 
public class EventController {
    @Autowired //automatically inject an instance of event repo
    private EventRepository eventRepository;

    @GetMapping
    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    // get event by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventByID(@PathVariable(value = "id") long eventID) {
        if (!this.eventRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        Event existingEvent = this.eventRepository.findById(eventID).get();
        return ResponseEntity.ok(existingEvent);
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<?> getNumTicketsSoldByID(@PathVariable(value = "id") long eventID) {
        if (!this.eventRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        Event existingEvent = this.eventRepository.findById(eventID).get();
        List<TicketingOption> ticketingOptionsList = existingEvent.getTicketingOptions();
        String response = "";
        int totalTicketsSold = 0;
        for (TicketingOption to : ticketingOptionsList){
            totalTicketsSold += to.getNumTicketsSold();
            response = response + to.getTierName() + ":" + to.getNumTicketsSold() +"\n";
        }
        response = response + "Total: " + totalTicketsSold;
        return ResponseEntity.ok(response);
    }

    @GetMapping("/revenue/{id}")
    public ResponseEntity<?> getRevenueByID(@PathVariable(value = "id") long eventID) {
        if (!this.eventRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        Event existingEvent = this.eventRepository.findById(eventID).get();
        List<TicketingOption> ticketingOptionsList = existingEvent.getTicketingOptions();
        String response = "";
        double totalRevenue = 0;
        for (TicketingOption to : ticketingOptionsList){
            totalRevenue += to.getTierRevenue();
            response = response + to.getTierName() + ":" + to.getTierRevenue() +"\n";
        }
        response = response + "Total: " + totalRevenue;
        return ResponseEntity.ok(response);
    }

    @PutMapping("/sell/{id}") // works
    public ResponseEntity<?> sellTickets(@RequestBody List<SellTicketsBody> request, @PathVariable(value="id") long eventID) {
        if (!this.eventRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        Event existingEvent = this.eventRepository.findById(eventID).get();
        List<TicketingOption> ticketingOptions = existingEvent.getTicketingOptions();
        for (SellTicketsBody tb : request){
            for (TicketingOption to : ticketingOptions){
                if (tb.getTierName().equals(to.getTierName())){
                    to.sellTickets(tb.getTierQty());
                }
            }
        }
        this.eventRepository.save(existingEvent);
        return ResponseEntity.ok(existingEvent);
    }

    @DeleteMapping("/{id}") // might need to consider the tickets and refund idk
    public ResponseEntity<?> deleteEvent(@PathVariable(value = "id") long eventID) {
        if (!this.eventRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        Event existingEvent = this.eventRepository.findById(eventID).get();
        this.eventRepository.delete(existingEvent);
        return ResponseEntity.ok("Event has been deleted");

    }
    
}
