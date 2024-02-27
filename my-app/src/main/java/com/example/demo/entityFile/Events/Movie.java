package com.example.demo.entityFile.Events;
import java.util.ArrayList;

public class Movie extends Event {
    private ArrayList<String> mainCast; // ["Taylor Swift", "John Smith"]
    private String rating; // "PG13"
    private int duration; // in minutes, to nearest minute e.g. 180
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

    public Movie(ArrayList<String> mainCast, ArrayList<TicketingOption> ticketingOptions, 
    String rating, int duration, String eventName, String eventVenue, String eventDate, 
    String eventTime, double ticketPrice, int numOfTicketsAvail, double cancellationFee){ // with cancellation fee

        // not sure if Event provide eventID itself or need to give it.
        super(eventName, eventVenue, eventDate, eventTime, ticketPrice, numOfTicketsAvail, cancellationFee);
        this.mainCast = mainCast;
        this.ticketingOptions = ticketingOptions;
        this.rating = rating;
        this.duration = duration;

    }

    public Movie(ArrayList<String> mainCast, ArrayList<TicketingOption> ticketingOptions, 
    String rating, int duration, String eventName, String eventVenue, String eventDate, 
    String eventTime, double ticketPrice, int numOfTicketsAvail){ // without cancellation fee

        // not sure if Event provide eventID itself or need to give it.
        super(eventName, eventVenue, eventDate, eventTime, ticketPrice, numOfTicketsAvail);
        this.mainCast = mainCast;
        this.ticketingOptions = ticketingOptions;
        this.rating = rating;
        this.duration = duration;

    }

    public ArrayList<String> getMainCast(){
        return this.mainCast;
    }

    public ArrayList<TicketingOption> getTicketingOptions(){
        return this.ticketingOptions;
    }

    public String getRating(){
        return this.rating;
    }

    public int getDuration(){
        return this.duration;
    }


}
