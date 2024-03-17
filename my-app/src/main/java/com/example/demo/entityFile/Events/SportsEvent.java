package com.example.demo.entityFile.Events;
import java.util.ArrayList;

import com.example.demo.entityFile.Ticketing.TicketingOption;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SPORTS_EVENT")
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

    public SportsEvent(){

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

    public void setParticipants(ArrayList<String> newParticipants){
        this.participants = newParticipants;
    }

    public void setTicketingOptions(ArrayList<TicketingOption> newTicketingOptions){
        this.ticketingOptions = newTicketingOptions;
    }

    public void setSport(String newSport){
        this.sport = newSport;
    }

    


}
