package com.example.demo.entityFile.Events;

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
    private String eventDate; // In the form of DDMMYYYY can we store as datetime? easier do calculation

    @NotNull(message = "Event must have a time!")
    @Column(name = "eventTime")
    private String eventTime; // In the form of 24h e.g. HHMM (2359)

    @NotNull(message = "Event must have ticketing options!")
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "event",  orphanRemoval = true)
    @Column(name = "ticketingOptions")
    private List<TicketingOption> ticketingOptions;

    @Column(name = "cancellationFee")
    private Double cancellationFee;

    @Column(name = "event_type", insertable =  false, updatable = false)
    protected String eventType;

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

    public Double getCancellationFee(){
        return this.cancellationFee;
    }

    public String getEventType(){
        return this.eventType;
    }

    // dont see the need for set event id

    public void setEventName(String newName){
        this.eventName = newName;
    } 

    public void setEventVenue(String newVenue){
        this.eventVenue = newVenue;
    } 

    public void setEventDate(String newDate){
        this.eventDate = newDate;
    } 
    
    public void setEventTime(String newTime){
        this.eventTime = newTime;
    } 

    // set via ticketingoptionscontroller
    // public void setTicketingOptions(List<TicketingOption> newTicketingOptions){
    //     this.ticketingOptions = newTicketingOptions;
    // } 

    public void setCancellationFee(Double newCancellationFee){
        this.cancellationFee = newCancellationFee;
    }
}