package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entityFile.Events.Event;
import com.example.demo.entityFile.Events.Ticket;
import com.example.demo.entityFile.Users.Customer;
import com.example.demo.exception.*;
import com.example.demo.repository.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ticket")
public class TicketController {
    private TicketRepository ticketRepository;
    private EventRepository eventRepository;
    private CustomerRepository customerRepository;

    @PostMapping("/createTicket/{numTickets}/{eventID}/{customerUsername}")
    public List<Ticket> createTicket(@PathVariable("numTickets") int numTickets, @PathVariable("eventID") long eventID, @PathVariable("customerUsername") String customerUsername) {
        //Should check if available here? or in the service layer?
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        //Check if the event exists
        Event event = eventRepository.findById(eventID)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id :" + eventID));
        if (event.getNumTicketsAvailable() < numTickets) {
            throw new IllegalArgumentException("Not enough tickets available");
        }
        //Check if customer exists
        Customer customer = customerRepository.findById(customerUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with username :" + customerUsername));
        if (customer.getAccountBalance() < numTickets * event.getTicketPrice()) {
            throw new IllegalArgumentException("Not enough balance");
        }
        //Check if allowed to book more than 24 hours before show and less than 6 months in advance
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        LocalDateTime date = LocalDateTime.parse(event.getEventDate(), dateFormatter);
        LocalDateTime time = LocalDateTime.parse(event.getEventTime(), timeFormatter);
        LocalDateTime eventDateTime = LocalDateTime.of(date.toLocalDate(), time.toLocalTime());
        if (eventDateTime.isBefore(LocalDateTime.now().plusDays(1))) {
            throw new IllegalArgumentException("Cannot book less than 24 hours before show");
        }
        if (eventDateTime.isAfter(LocalDateTime.now().plusMonths(6))) {
            throw new IllegalArgumentException("Cannot book more than 6 months in advance");
        }

        //Update values and make booking
        customer.setAccountBalance((float)(customer.getAccountBalance() - numTickets * event.getTicketPrice()));
        customerRepository.save(customer);
        event.updateNumTicketsAvailable(event.getNumTicketsAvailable() - numTickets);
        event.updateNumTicketsSold(event.getNumTicketsSold() + numTickets);
        eventRepository.save(event);

        for (int i = 0; i < numTickets; i++) {
            Ticket ticket = new Ticket(event, customer);
            ticketRepository.save(ticket);
            tickets.add(ticket);
        }
        return tickets;
    }

    //Allow customers to cancel tickets
    @DeleteMapping("/Cancellation/{ticketID}")
    public String deleteTicket(@PathVariable("ticketID") int ticketID) {
        Ticket ticket = ticketRepository.findById(ticketID)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id :" + ticketID));
        
        //Check if allowed to cancel
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        LocalDateTime date = LocalDateTime.parse(ticket.getEvent().getEventDate(), dateFormatter);
        LocalDateTime time = LocalDateTime.parse(ticket.getEvent().getEventTime(), timeFormatter);
        LocalDateTime eventDateTime = LocalDateTime.of(date.toLocalDate(), time.toLocalTime());
        if (eventDateTime.isBefore(LocalDateTime.now().plusDays(1))) {
            throw new IllegalArgumentException("Cannot cancel less than 24 hours before show");
        }

        //Refund
        Customer customer = ticket.getCustomer();
        customer.setAccountBalance((float)(customer.getAccountBalance() + ticket.getEvent().getTicketPrice() - ticket.getEvent().getCancellationFee()));
        customerRepository.save(customer);

        //Update values
        Event event = ticket.getEvent();
        event.updateNumTicketsAvailable(event.getNumTicketsAvailable() + 1);
        event.updateNumTicketsSold(event.getNumTicketsSold() - 1);
        eventRepository.save(event);


        ticketRepository.delete(ticket);
        return "Ticket deleted successfully " + ticketID;
    }

    //Cancellation of event
    @DeleteMapping("/CancellationByEvent/{eventID}")
    public String deleteTicketByEvent(@PathVariable("eventID") long eventID) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketRepository.findAll()) {
            if (ticket.getEventID() == eventID) {
                tickets.add(ticket);
            }
        }
        for (Ticket ticket : tickets) {
            //Refund
            Customer customer = ticket.getCustomer();
            customer.setAccountBalance((float)(customer.getAccountBalance() + ticket.getEvent().getTicketPrice() - ticket.getEvent().getCancellationFee()));
            deleteTicket(ticket.getTicketID());
        }
        return "Tickets deleted successfully for event " + eventID;
    }

    @GetMapping("/getTicket/{ticketID}")
    public Ticket getTicketById(@PathVariable("ticketID") int ticketID) {
        return ticketRepository.findById(ticketID)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id :" + ticketID));
    }

    //Getting tickets of a certain event
    @GetMapping("/getTicketsByEvent/{eventID}")
    public List<Ticket> getTicketsByEventId(@PathVariable("eventID") int eventID) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketRepository.findAll()) {
            if (ticket.getEventID() == eventID) {
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    //Getting tickets of a certain customer Can be used for booking history
    @GetMapping("/getTicketsByCustomer/{username}")
    public List<Ticket> getTicketsByCustomer(@PathVariable("username") String username) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketRepository.findAll()) {
            if (ticket.getCustomerUsername().equals(username)) {
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    //Checking validity of a ticket (I need to make more specific errors like if eventID not matching and if customerusername not matching)
    @GetMapping("/checkTicket/{ticketID}/{eventID}/{customerUsername}")
    public boolean checkTicket(@PathVariable("ticketID") int ticketID, @PathVariable("eventID") int eventID, @PathVariable("customerUsername") String customerUsername) {
        Ticket ticket = ticketRepository.findById(ticketID)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id :" + ticketID));
        return ticket.getEventID() == eventID && ticket.getCustomerUsername().equals(customerUsername);
    }


    


    
}
