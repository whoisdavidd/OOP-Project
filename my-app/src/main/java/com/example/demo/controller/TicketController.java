package com.example.demo.controller;

import com.example.demo.entityFile.Events.Event;
import com.example.demo.entityFile.Ticketing.Ticket;
import com.example.demo.entityFile.Ticketing.TicketingOption;
import com.example.demo.entityFile.Users.Customer;
import com.example.demo.exception.*;
import com.example.demo.repository.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ticket")
public class TicketController {
    private TicketRepository ticketRepository;
    private EventRepository eventRepository;
    private CustomerRepository customerRepository;
    private TicketingOptionRepository ticketingOptionRepository;

    @PostMapping("/createTicket/{numTickets}/{eventID}/{ticketingOptionID}/{customerUsername}")
    public List<Ticket> createTicket(@PathVariable("numTickets") int numTickets, @PathVariable("eventID") long eventID, @PathVariable("customerUsername") String customerUsername, @PathVariable("ticketingOption") long ticketingOptionID) {
        //Should check if available here? or in the service layer?
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        //Check if the event exists
        Event event = eventRepository.findById(eventID)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id :" + eventID));
        List<TicketingOption> ticketingOptions = event.getTicketingOptions();
        TicketingOption currentOption = null;
        for (TicketingOption ticketingOption : ticketingOptions) {
            if (ticketingOption.getTicketingOptionID() == ticketingOptionID) {
                currentOption = ticketingOption;
                break;
            }
        }
        if (currentOption == null) {
            throw new ResourceNotFoundException("Ticketing option not found:" + ticketingOptionID);
        }

        if (currentOption.getTierQuantity() - currentOption.getNumTicketsSold() < numTickets) {
            throw new IllegalArgumentException("Not enough tickets available");
        }
        //Check if customer exists
        Customer customer = customerRepository.findById(customerUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with username :" + customerUsername));
        if (customer.getAccountBalance() < numTickets * currentOption.getTierPrice()) {
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
        customer.setAccountBalance((float)(customer.getAccountBalance() - numTickets * currentOption.getTierPrice()));
        customerRepository.save(customer);
        currentOption.sellTickets(numTickets);
        ticketingOptionRepository.save(currentOption);
        eventRepository.save(event);

        for (int i = 0; i < numTickets; i++) {
            Ticket ticket = new Ticket(event, customer, currentOption.getTierPrice(), currentOption);
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
        customer.setAccountBalance((float)(customer.getAccountBalance() + ticket.getPrice() - ticket.getEvent().getCancellationFee()));
        customerRepository.save(customer);

        //Update values
        Event event = ticket.getEvent();
        //Need option to unsell ticket?
        List<TicketingOption> ticketingOptions = event.getTicketingOptions();
        TicketingOption currentOption = null;
        for (TicketingOption ticketingOption : ticketingOptions) {
            if (ticketingOption.getTicketingOptionID() == ticket.getTicketingOption().getTicketingOptionID()) {
                currentOption = ticketingOption;
                break;
            }
        }

        currentOption.refundTicket(ticket.getPrice() - ticket.getEvent().getCancellationFee());
        eventRepository.save(event);
        ticketingOptionRepository.save(currentOption);
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
            customer.setAccountBalance((float)(customer.getAccountBalance() + ticket.getPrice() - ticket.getEvent().getCancellationFee()));
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
