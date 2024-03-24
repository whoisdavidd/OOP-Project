package com.example.demo.entityFile.Ticketing;

import jakarta.persistence.*;


import com.example.demo.entityFile.Events.Event;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "TicketingOption")
public class TicketingOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketingOptionID;

    @Column(name = "tierName")
    private String tierName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull(message = "Ticketing option must be associated with an event!")
    @JsonIgnore
    @JoinColumn(name = "eventID")
    private Event event;

    @NotNull(message = "Tier must have a price!")
    @Column(name = "tierPrice")
    private Double tierPrice;

    @NotNull(message = "Tier must have a quantity!")
    @Column(name = "tierQuantity")
    private Integer tierQuantity;
    
    @Column(name = "numTicketsSold")
    private Integer numTicketsSold;

    @Column(name = "tierRevenue")
    private Double tierRevenue;

    public TicketingOption(){
        this.numTicketsSold = 0;
        this.tierRevenue = 0.0;
    }

    public String getTierName(){
        return this.tierName;
    }

    public Long getTicketingOptionID(){
        return this.ticketingOptionID;
    }

    public double getTierPrice(){
        return this.tierPrice;
    }

    public int getTierQuantity(){
        return this.tierQuantity;
    }

    public int getNumTicketsSold(){
        return this.numTicketsSold;
    }

    public Event getEvent(){
        return this.event;
    }

    public double getTierRevenue(){
        return this.tierRevenue; 
    }

    public void setEvent(Event newEvent){
        this.event = newEvent;
    }

    public void setTierPrice(double newTierPrice){
        this.tierPrice = newTierPrice;
    }

    public void sellTickets(int numOfTickets){
        this.tierRevenue += (this.tierPrice * numOfTickets);
        this.numTicketsSold += numOfTickets;
    }

    // use with caution, shouldn't set tier quantity randomly
    public void setTierQuantity(int newTierQuantity){
        this.tierQuantity = newTierQuantity;
    }

    
}
