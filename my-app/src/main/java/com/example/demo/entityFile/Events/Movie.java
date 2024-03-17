package com.example.demo.entityFile.Events;
import java.util.ArrayList;

import com.example.demo.entityFile.Ticketing.TicketingOption;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOVIE")
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

    public Movie(){

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

    public void setMainCast(ArrayList<String> newMainCast){
        this.mainCast = newMainCast;
    }

    public void setTicketingOptions(ArrayList<TicketingOption> newTicketingOptions){
        this.ticketingOptions = newTicketingOptions;
    }

    public void setRating(String newRating){
        this.rating = newRating;
    }

    public void setDuration(int newDuration){
        this.duration = newDuration;
    }

}
