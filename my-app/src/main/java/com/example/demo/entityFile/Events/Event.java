package com.example.demo.entityFile.Events;


import java.util.ArrayList;
import java.util.List;

import com.example.demo.entityFile.Ticketing.TicketingOption;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "event_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventID;

    @NotNull(message = "Event must have a name!")
    @Column(name = "eventName")
    private String eventName;

    @NotNull(message = "Event must have a venue!")
    @Column(name = "eventVenue")
    private String eventVenue;

    @NotNull(message = "Event must have a date!")
    @Column(name = "eventDate")
    private String eventDate; // In the form of DDMMYYYY

    @NotNull(message = "Event must have a time!")
    @Column(name = "eventTime")
    private String eventTime; // In the form of 24h e.g. HHMM (2359)

    @NotNull(message = "Event must have ticketing options!")
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "event",  orphanRemoval = true)
    @Column(name = "ticketingOptions")
    private List<TicketingOption> ticketingOptions;

    @NotNull(message = "Event must have a number of available tickets!")
    @Column(name = "numTicketsAvailable")
    private Integer numTicketsAvailable; 

    @Column(name = "numTicketsSold")
    private Integer numTicketsSold; 

    @Column(name = "cancellationFee")
    private Double cancellationFee;

    // constructor

    public Event(){

    }
    
    // getter methods
    
    public long getEventID(){
        return this.eventID;
    }
    public String getEventName(){
        return this.eventName;
    }
    
    public String getEventVenue(){
        return this.eventVenue;
    }
    public String getEventDate(){
        return this.eventDate;
    }
    public String getEventTime(){
        return this.eventTime;
    }
    public List<TicketingOption> getTicketingOptions(){
        return this.ticketingOptions;
    }
    public Integer getNumTicketsAvailable(){
        return this.numTicketsAvailable;
    }

    public Integer getNumTicketsSold(){
        return this.numTicketsSold;
    }

    public Double getCancellationFee(){
        return this.cancellationFee;
    }

    // setter/update methods

    // dont see the need for update event id

    public void updateEventName(String newName){
        this.eventName = newName;
    } 

    public void updateEventVenue(String newVenue){
        this.eventVenue = newVenue;
    } 

    public void updateEventDate(String newDate){
        this.eventDate = newDate;
    } 
    
    public void updateEventTime(String newTime){
        this.eventTime = newTime;
    } 

    public void updateTicketOptions(List<TicketingOption> newTicketingOptions){
        this.ticketingOptions = newTicketingOptions;
    } 
    
    public void updateNumTicketsAvailable(Integer newNumOfTickets){
        this.numTicketsAvailable = newNumOfTickets;
    } 
    
    public void updateNumTicketsSold(Integer newNumOfTickets){
        this.numTicketsSold = newNumOfTickets;
    } 

    public void updateCancellationFee(Double newCancellationFee){
        this.cancellationFee = newCancellationFee;
    }
}