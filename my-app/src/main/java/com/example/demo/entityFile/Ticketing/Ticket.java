package com.example.demo.entityFile.Ticketing;

import java.util.Date;

import com.example.demo.entityFile.Users.Customer;
import com.example.demo.entityFile.Events.Event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketID;
    
    @ManyToOne
    @JoinColumn(name="eventID")
    private Event event;

    @ManyToOne
    @JoinColumn(name="username")
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate;

    private double price;

    @ManyToOne
    @JoinColumn(name="ticketingOptionId")
    private TicketingOption ticketingOption;

    @PrePersist
    protected void onCreate() {
        bookingDate = new Date();
    }

    public Ticket() {
    }

    public Ticket(Event event, Customer customer, double price, TicketingOption ticketingOption) {
        this.event = event;
        this.customer = customer;
        this.price = price;
        this.ticketingOption = ticketingOption;
    }


    // Getters and setters for the attributes

    public int getTicketID() {
        return ticketID;
    }

    public Event getEvent() {
        return event;
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getEventID() {
        return this.event.getEventID();
    }

    public String getCustomerUsername() {
        return this.customer.getUsername();
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public double getPrice() {
        return price;
    }

    public TicketingOption getTicketingOption() {
        return ticketingOption;
    }

}

