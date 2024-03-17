package com.example.demo.entityFile.Events;
import java.util.ArrayList;

import com.example.demo.entityFile.Ticketing.TicketingOption;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONCERT")
public class Concert extends Event {
    private ArrayList<String> performers; // ["Taylor Swift", "John Smith"]
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

    public Concert(){

    }
    
    public ArrayList<String> getPerformers(){
        return this.performers;
    }

    public ArrayList<TicketingOption> getTicketingOptions(){
        return this.ticketingOptions;
    }

    public void setPerformers(ArrayList<String> newPerformers){
        this.performers = newPerformers;
    }

    public void setTicketingOptions(ArrayList<TicketingOption> newTicketingOptions){
        this.ticketingOptions = newTicketingOptions;
    }


}
