package com.example.demo.entityFile.Events;

import jakarta.persistence.*;
@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eventID;

    @Column(name = "eventName")
    private String eventName;

    @Column(name = "eventVenue")
    private String eventVenue;

    @Column(name = "eventDate")
    private String eventDate; // In the form of DDMMYYYY

    @Column(name = "eventTime")
    private String eventTime; // In the form of 24h e.g. HHMM (2359)

    @Column(name = "ticketPrice")
    private double ticketPrice;

    @Column(name = "numTicketsAvailable")
    private int numTicketsAvailable; 

    @Column(name = "numTicketsSold")
    private int numTicketsSold; 

    @Column(name = "cancellationFee")
    private double cancellationFee;

    // constructor

    public Event(){

    }

    public Event(String name, String eventVenue, String eventDate, 
    String eventTime, double ticketPrice, int numTicketsAvailable){ // without cancellation fee
        
        // Automatically create eventID based on latest eventID?
        this.eventName = name;
        this.eventVenue = eventVenue;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.ticketPrice = ticketPrice;
        this.numTicketsAvailable = numTicketsAvailable;
        this.cancellationFee = 0;

    }

    public Event(String name, String eventVenue, String eventDate, 
    String eventTime, double ticketPrice, int numTicketsAvailable, double cancellationFee){ // with cancellation fee

        // Automatically create eventID based on latest eventID?
        this.eventName = name;
        this.eventVenue = eventVenue;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.ticketPrice = ticketPrice;
        this.numTicketsAvailable = numTicketsAvailable;
        this.cancellationFee = cancellationFee;

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
    public double getTicketPrice(){
        return this.ticketPrice;
    }
    public int getNumTicketsAvailable(){
        return this.numTicketsAvailable;
    }

    public int getNumTicketsSold(){
        return this.numTicketsSold;
    }

    public double getCancellationFee(){
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

    public void updateTicketPrice(double newTicketPrice){
        this.ticketPrice = newTicketPrice;
    } 
    
    public void updateNumTicketsAvailable(int newNumOfTickets){
        this.numTicketsAvailable = newNumOfTickets;
    } 
    
    public void updateNumTicketsSold(int newNumOfTickets){
        this.numTicketsSold = newNumOfTickets;
    } 

    public void updateCancellationFee(double newCancellationFee){
        this.cancellationFee = newCancellationFee;
    }
}