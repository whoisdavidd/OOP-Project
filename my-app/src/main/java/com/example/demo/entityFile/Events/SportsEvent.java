package com.example.demo.entityFile.Events;
import java.util.ArrayList;

public class SportsEvent extends Event {
    private ArrayList<String> participants; // ["Liverpool", "Manchester United"]
    private ArrayList<TicketingOption> ticketingOptions; 
        /* 
        [
            { // Object named TicketingOption
            "tierName" : "Cat 1", // String for tierName
            "tierPrice" : 500.0, // double for tierPrice
            "tierQty" : 20 // int for tierQty
            }
        ]
    */ 
    private String sport;



    public SportsEvent(ArrayList<String> participants, ArrayList<TicketingOption> ticketingOptions, 
    String sport, String eventName,  String eventVenue, String eventDate, String eventTime,
    double ticketPrice, int numOfTicketsAvail, double cancellationFee){ // with cancellation fee

        // not sure if Event provide eventID itself or need to give it.
        super(eventName, eventVenue, eventDate, eventTime, ticketPrice, numOfTicketsAvail, cancellationFee);
        this.sport = sport;
        this.participants = participants;
        this.ticketingOptions = ticketingOptions;

    }

    public SportsEvent(ArrayList<String> participants, ArrayList<TicketingOption> ticketingOptions, 
    String sport, String eventName,  String eventVenue, String eventDate, String eventTime,
    double ticketPrice, int numOfTicketsAvail){ // without cancellation fee

        // not sure if Event provide eventID itself or need to give it.        
        super(eventName, eventVenue, eventDate, eventTime, ticketPrice, numOfTicketsAvail);
        this.sport = sport;
        this.participants = participants;
        this.ticketingOptions = ticketingOptions;

    }

    public ArrayList<String> getParticipants(){
        return this.participants;
    }

    public ArrayList<TicketingOption> getTicketingOptions(){
        return this.ticketingOptions;
    }

    public String getSport(){
        return this.sport;
    }


}
