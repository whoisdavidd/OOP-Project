package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entityFile.Events.Event;
import com.example.demo.exception.*;
import com.example.demo.repository.EventRepository;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired //automatically inject an instance of event repo
    private EventRepository eventRepository;

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        Event savedEvent = this.eventRepository.save(event);
        this.eventRepository.save(event);
        return savedEvent;
    }
    @PutMapping("/{id}")
    public Event updateEvent(@RequestBody Event event, @PathVariable("id") long eventId) {
        Event existingEvent = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id :" + eventId));
        existingEvent.updateEventName(event.getEventName());
        existingEvent.updateEventVenue(event.getEventVenue());
        existingEvent.updateEventDate(event.getEventDate());
        existingEvent.updateEventTime(event.getEventTime());
        existingEvent.updateTicketPrice(event.getTicketPrice());
        existingEvent.updateNumTicketsAvailable(event.getNumTicketsAvailable());
        existingEvent.updateNumTicketsSold(event.getNumTicketsSold());
        existingEvent.updateCancellationFee(event.getCancellationFee());
        return this.eventRepository.save(existingEvent);
    }
    @GetMapping
    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    // get event by id
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable(value = "id") long eventId) {
        return this.eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id :" + eventId));
    }

    @GetMapping("/sales/{id}")
    public int getNumTicketsSoldById(@PathVariable(value = "id") long eventId) {
        Event e = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id :" + eventId));
        
        return e.getNumTicketsSold();
    }

    @GetMapping("/revenue/{id}")
    public double getRevenueById(@PathVariable(value = "id") long eventId) {
        Event e = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id :" + eventId));
        
        return e.getNumTicketsSold() * e.getTicketPrice();
    }

    @DeleteMapping("/{id}") // might need to consider the tickets and refund idk
    public ResponseEntity<Event> deleteEvent(@PathVariable(value = "id") long eventId) {
        Event existingEvent = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id :" + eventId));
        this.eventRepository.delete(existingEvent);
        return ResponseEntity.ok().build();

    }
}
