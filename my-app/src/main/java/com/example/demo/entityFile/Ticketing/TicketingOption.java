package com.example.demo.entityFile.Ticketing;

import jakarta.persistence.*;
import com.example.demo.entityFile.Events.Event;
import jakarta.validation.constraints.*;

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
    @JoinColumn(name = "eventID")
    private Event event;

    @NotNull(message = "Tier must have a price!")
    @Column(name = "tierPrice")
    private Double tierPrice;

    @NotNull(message = "Tier must have a quantity!")
    @Column(name = "tierQuantity")
    private Integer tierQuantity;

    public TicketingOption(){

    }

    public String getTierName(){
        return this.tierName;
    }

    public double getTierPrice(){
        return this.tierPrice;
    }

    public int getTierQuantity(){
        return this.tierQuantity;
    }

    public void setEvent(Event newEvent){
        this.event = newEvent;
    }
}
