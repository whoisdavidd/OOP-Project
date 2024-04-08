package com.example.demo.controller;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

import com.example.demo.entityFile.Events.Event;
import com.example.demo.entityFile.Ticketing.Ticket;
import com.example.demo.entityFile.Ticketing.TicketingOption;
import com.example.demo.entityFile.Users.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.TicketingOptionRepository;


@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TicketingOptionRepository ticketingOptionRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    
    @PostMapping("/createTicket/{numTickets}/{eventID}/{ticketingOptionID}/{customerUsername}")
    public ResponseEntity<?> createTicket(@PathVariable("numTickets") int numTickets, @PathVariable("eventID") long eventID, @PathVariable("customerUsername") String customerUsername, @PathVariable("ticketingOptionID") long ticketingOptionID) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        Optional<Event> eventOptional = eventRepository.findById(eventID);
        if (!eventOptional.isPresent()) {
            return new ResponseEntity<>("Event not found with id :" + eventID, HttpStatus.NOT_FOUND);
        }
        Event event = eventOptional.get();
        List<TicketingOption> ticketingOptions = event.getTicketingOptions();
        TicketingOption currentOption = null;
        for (TicketingOption ticketingOption : ticketingOptions) {
            if (ticketingOption.getTicketingOptionID() == ticketingOptionID) {
                currentOption = ticketingOption;
                break;
            }
        }
        if (currentOption == null) {
            return new ResponseEntity<>("Ticketing option not found:" + ticketingOptionID, HttpStatus.NOT_FOUND);
        }

        if (currentOption.getTierQuantity() - currentOption.getNumTicketsSold() < numTickets) {
            return new ResponseEntity<>("Not enough tickets available", HttpStatus.BAD_REQUEST);
        }

        Optional<Customer> customerOptional = customerRepository.findById(customerUsername);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>("Customer not found with username :" + customerUsername, HttpStatus.NOT_FOUND);
        }
        Customer customer = customerOptional.get();
        if (customer.getAccountBalance() < numTickets * currentOption.getTierPrice()) {
            return new ResponseEntity<>("Not enough balance", HttpStatus.BAD_REQUEST);
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate date = LocalDate.parse(event.getEventDate(), dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(event.getEventTime(), timeFormatter);

        LocalDateTime eventDateTime = LocalDateTime.of(date, time);
        if (eventDateTime.isBefore(LocalDateTime.now().plusDays(1))) {
            return new ResponseEntity<>("Cannot book less than 24 hours before show", HttpStatus.BAD_REQUEST);
        }
        if (eventDateTime.isAfter(LocalDateTime.now().plusMonths(6))) {
            return new ResponseEntity<>("Cannot book more than 6 months in advance", HttpStatus.BAD_REQUEST);
        }
        customer.setAccountBalance((float)(customer.getAccountBalance() - numTickets * currentOption.getTierPrice()));
        customerRepository.save(customer);
        currentOption.sellTickets(numTickets);
        ticketingOptionRepository.save(currentOption);
        eventRepository.save(event);
        StringBuilder emailText = new StringBuilder("Dear customer,\n\nThank you for your purchase. Here are your e-ticket details:\n\n");
        for (int i = 0; i < numTickets; i++) {
            Ticket ticket = new Ticket(event, customer, currentOption.getTierPrice(), currentOption);
            ticketRepository.save(ticket);
            tickets.add(ticket);

            emailText.append("Ticket ID: ").append(ticket.getTicketID()).append("\n")
            .append("Event: ").append(event.getEventName()).append("\n")
            .append("Price: ").append(currentOption.getTierPrice()).append("\n")
            .append("Ticketing Option: ").append(currentOption.getTierName()).append("\n\n");
        }
        emailText.append("Please present these e-tickets at the event entrance.\n\nBest regards,\n Ticket Mister Team");
        sendEmail(customer.getEmailAddress(), "Ticket Mister: Booking Confirmation", emailText.toString());
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PostMapping("/onsiteSale/{numTickets}/{eventID}/{ticketingOptionID}/{email}")
    public ResponseEntity<?> createTicketForSale(@PathVariable("numTickets") int numTickets, @PathVariable("eventID") long eventID, @PathVariable("ticketingOption") long ticketingOptionID, @PathVariable("email") String email) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        Optional<Event> eventOptional = eventRepository.findById(eventID);
        if (!eventOptional.isPresent()) {
            return new ResponseEntity<>("Event not found with id :" + eventID, HttpStatus.NOT_FOUND);
        }
        Event event = eventOptional.get();
        List<TicketingOption> ticketingOptions = event.getTicketingOptions();
        TicketingOption currentOption = null;
        for (TicketingOption ticketingOption : ticketingOptions) {
            if (ticketingOption.getTicketingOptionID() == ticketingOptionID) {
                currentOption = ticketingOption;
                break;
            }
        }
        if (currentOption == null) {
            return new ResponseEntity<>("Ticketing option not found:" + ticketingOptionID, HttpStatus.NOT_FOUND);
        }

        if (currentOption.getTierQuantity() - currentOption.getNumTicketsSold() < numTickets) {
            return new ResponseEntity<>("Not enough tickets available", HttpStatus.BAD_REQUEST);
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate date = LocalDate.parse(event.getEventDate(), dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(event.getEventTime(), timeFormatter);

        LocalDateTime eventDateTime = LocalDateTime.of(date, time);
        if (eventDateTime.isBefore(LocalDateTime.now().plusDays(1))) {
            return new ResponseEntity<>("Cannot book less than 24 hours before show", HttpStatus.BAD_REQUEST);
        }
        if (eventDateTime.isAfter(LocalDateTime.now().plusMonths(6))) {
            return new ResponseEntity<>("Cannot book more than 6 months in advance", HttpStatus.BAD_REQUEST);
        }

        //Creating a dummy customer to store this booking
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        String url = "http://localhost:8080/api/customer";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\", \"emailAddress\": \"" + email + "\" }";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        try {
            restTemplate.postForEntity(url, entity, String.class);
        } catch (Exception e) {
            // Handle error
        }

        Optional<Customer> customerOptional = customerRepository.findById(username);
        Customer customer = customerOptional.get();

        currentOption.sellTickets(numTickets);
        ticketingOptionRepository.save(currentOption);
        eventRepository.save(event);

        StringBuilder emailText = new StringBuilder("Dear customer,\n\nThank you for your purchase. Here are your e-ticket details:\n\n");
        for (int i = 0; i < numTickets; i++) {
            Ticket ticket = new Ticket(event, customer, currentOption.getTierPrice(), currentOption);
            ticketRepository.save(ticket);
            tickets.add(ticket);

            emailText.append("Ticket ID: ").append(ticket.getTicketID()).append("\n")
            .append("Event: ").append(event.getEventName()).append("\n")
            .append("Price: ").append(currentOption.getTierPrice()).append("\n")
            .append("Ticketing Option: ").append(currentOption.getTierName()).append("\n\n");
        }
        emailText.append("Please present these e-tickets at the event entrance.\n\nBest regards,\n Ticket Mister Team");
        sendEmail(customer.getEmailAddress(), "Ticket Mister: Booking Confirmation", emailText.toString());
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    void sendEmail(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }

    //Allow customers to cancel tickets
    @DeleteMapping("/Cancellation/{ticketID}")
    public ResponseEntity<?> deleteTicket(@PathVariable("ticketID") int ticketID) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketID);
        if (!ticketOptional.isPresent()) {
            return new ResponseEntity<>("Ticket not found with id :" + ticketID, HttpStatus.NOT_FOUND);
        }
        Ticket ticket = ticketOptional.get();

        // Check if allowed to cancel
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        LocalDateTime date = LocalDateTime.parse(ticket.getEvent().getEventDate(), dateFormatter);
        LocalDateTime time = LocalDateTime.parse(ticket.getEvent().getEventTime(), timeFormatter);
        LocalDateTime eventDateTime = LocalDateTime.of(date.toLocalDate(), time.toLocalTime());
        if (eventDateTime.isBefore(LocalDateTime.now().plusDays(1))) {
            return new ResponseEntity<>("Cannot cancel less than 24 hours before show", HttpStatus.BAD_REQUEST);
        }

        // Refund
        Customer customer = ticket.getCustomer();
        customer.setAccountBalance((float)(customer.getAccountBalance() + ticket.getPrice() - ticket.getEvent().getCancellationFee()));
        customerRepository.save(customer);

        // Update values
        Event event = ticket.getEvent();
        List<TicketingOption> ticketingOptions = event.getTicketingOptions();
        TicketingOption currentOption = null;
        for (TicketingOption ticketingOption : ticketingOptions) {
            if (ticketingOption.getTicketingOptionID() == ticket.getTicketingOption().getTicketingOptionID()) {
                currentOption = ticketingOption;
                break;
            }
        }
        if(currentOption == null){
            return new ResponseEntity<>("Ticketing option not found:" + ticket.getTicketingOption().getTicketingOptionID(), HttpStatus.NOT_FOUND);
        }
        currentOption.refundTicket(ticket.getPrice() - ticket.getEvent().getCancellationFee());
        eventRepository.save(event);
        ticketingOptionRepository.save(currentOption);
        ticketRepository.delete(ticket);
        return new ResponseEntity<>("Ticket deleted successfully " + ticketID, HttpStatus.OK);
    }

    //Cancellation of event
    @DeleteMapping("/CancellationByEvent/{eventID}")
    public ResponseEntity<?> deleteTicketByEvent(@PathVariable("eventID") long eventID) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketRepository.findAll()) {
            if (ticket.getEvent().getEventID() == eventID) {
                tickets.add(ticket);
            }
        }
        if (tickets.isEmpty()) {
            return new ResponseEntity<>("No tickets found for event with id :" + eventID, HttpStatus.NOT_FOUND);
        }
        for (Ticket ticket : tickets) {
            //Refund
            Customer customer = ticket.getCustomer();
            customer.setAccountBalance((float)(customer.getAccountBalance() + ticket.getPrice()));
            customerRepository.save(customer);
            ticketRepository.delete(ticket);
            sendCancellationEmail(customer.getEmailAddress(), ticket.getEvent().getEventName(), ticket.getTicketID());
        }
        return new ResponseEntity<>("Tickets deleted successfully for event " + eventID, HttpStatus.OK);
    }
    private void sendCancellationEmail(String email, String eventName, long ticketId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Event Cancellation");
        message.setText("Dear customer,\n\n" +
        "We regret to inform you that the event '" + eventName + "' has been cancelled. Your ticket with ID " + ticketId + " has been fully refunded.\n\n" +
        "If you do not have an account and purchased the tickets onsite, please reach out to our customer service to process the refund.\n\n" +
        "We apologize for any inconvenience caused.\n\n" +
        "Best regards,\n" +
        "Ticket Mister Team");
        javaMailSender.send(message);
    }

    @GetMapping("/getTicket/{ticketID}")
    public ResponseEntity<?> getTicketById(@PathVariable("ticketID") int ticketID) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketID);
        if (!ticketOptional.isPresent()) {
            return new ResponseEntity<>("Ticket not found with id :" + ticketID, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketOptional.get(), HttpStatus.OK);
    }

    //Getting tickets of a certain event
    @GetMapping("/getTicketsByEvent/{eventID}")
    public ResponseEntity<?> getTicketsByEventId(@PathVariable("eventID") int eventID) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketRepository.findAll()) {
            if (ticket.getEvent().getEventID() == eventID) {
                tickets.add(ticket);
            }
        }
        if (tickets.isEmpty()) {
            return new ResponseEntity<>("No tickets found for event with id :" + eventID, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    //Getting tickets of a certain customer Can be used for booking history
    @GetMapping("/getTicketsByCustomer/{username}")
    public ResponseEntity<?> getTicketsByCustomer(@PathVariable("username") String username) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketRepository.findAll()) {
            if (ticket.getCustomerUsername().equals(username)) {
                tickets.add(ticket);
            }
        }
        if (tickets.isEmpty()) {
            return new ResponseEntity<>("No tickets found for customer with username :" + username, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    //Checking validity of a ticket (I need to make more specific errors like if eventID not matching and if customerusername not matching)
    @GetMapping("/checkTicket/{ticketID}/{eventID}")
    public ResponseEntity<?> checkTicket(@PathVariable("ticketID") int ticketID, @PathVariable("eventID") int eventID) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketID);
        if (!ticketOptional.isPresent()) {
            return new ResponseEntity<>("Ticket not found with id :" + ticketID, HttpStatus.NOT_FOUND);
        }
        Ticket ticket = ticketOptional.get();
        if (ticket.getEventID() != eventID) {
            return new ResponseEntity<>("Event ID does not match", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Ticket is valid", HttpStatus.OK);
    }

    @PutMapping("/markAttendance/{ticketID}")
    public ResponseEntity<?> markAttendance(@PathVariable("ticketID") int ticketID) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketID);
        if (!ticketOptional.isPresent()) {
            return new ResponseEntity<>("Ticket not found with id :" + ticketID, HttpStatus.NOT_FOUND);
        }
        Ticket ticket = ticketOptional.get();
        ticket.setAttended(true);
        ticketRepository.save(ticket);
        return new ResponseEntity<>("Ticket attendance marked successfully " + ticketID, HttpStatus.OK);
    }

    @GetMapping("/getAttendance/{eventID}")
    public ResponseEntity<?> getAttendance(@PathVariable("eventID") long eventID) {
        List<HashMap<String, Object>> attendanceInfo = new ArrayList<>();

        Event event = eventRepository.findById(eventID).get();
        if(event == null){
            return new ResponseEntity<>("Event not found with id :" + eventID, HttpStatus.NOT_FOUND);
        }
        List<TicketingOption> ticketingOptions = event.getTicketingOptions();
        for (TicketingOption ticketingOption : ticketingOptions) {
            int numTicketsAttended = 0;
            for (Ticket ticket : ticketRepository.findAll()) {
                if (ticket.getEventID() == eventID && ticket.didAttend() && ticket.getTicketingOption().getTicketingOptionID() == ticketingOption.getTicketingOptionID()) {
                    numTicketsAttended++;
                }
            }
            HashMap<String, Object> ticketInfo = new HashMap<>();
            ticketInfo.put("ticketOption", ticketingOption);
            ticketInfo.put("sold", ticketingOption.getNumTicketsSold());
            ticketInfo.put("attended", numTicketsAttended);
            attendanceInfo.add(ticketInfo);
            
        }
        return new ResponseEntity<>(attendanceInfo, HttpStatus.OK);
    }

}
