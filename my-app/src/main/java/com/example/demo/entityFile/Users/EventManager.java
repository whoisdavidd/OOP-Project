package com.example.demo.entityFile.Users;

import java.util.ArrayList;

import com.example.demo.entityFile.Events.Event;

public class EventManager{
    // variables
    @Id
    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;



    // constructor
    
    public EventManager(){

    }

    // adding events metho

    // getter method

    public ArrayList<Event> getEvents(){
        return this.eventList;
    }

    // update methods below

    public void updateEventVenue(String eventName, String newVenue){
        for (int i=0; i<this.eventList.size();i++){ // loops through whole list of Events, checks for same eventName, updates (could have duplicate names, might need change)
            if (this.eventList.get(i).getEventName().equals(eventName)){
                this.eventList.get(i).updateEventVenue(newVenue);
            }
        }
    } 

    public void updateEventDate(String eventName, String newDate){
        for (int i=0; i<this.eventList.size();i++){
            if (this.eventList.get(i).getEventName().equals(eventName)){
                this.eventList.get(i).updateEventDate(newDate);
            }
        }
    } 
    
    public void updateEventTime(String eventName, String newTime){
        for (int i=0; i<this.eventList.size();i++){
            if (this.eventList.get(i).getEventName().equals(eventName)){
                this.eventList.get(i).updateEventTime(newTime);
            }
        }
    } 

    public void updateEventPrice(String eventName, double newTicketPrice){
        for (int i=0; i<this.eventList.size();i++){
            if (this.eventList.get(i).getEventName().equals(eventName)){
                this.eventList.get(i).updateTicketPrice(newTicketPrice);
            }
        }
    } 
    
    public void updateEventTicketNum(String eventName, int newNumOfTickets){
        for (int i=0; i<this.eventList.size();i++){
            if (this.eventList.get(i).getEventName().equals(eventName)){
                this.eventList.get(i).updateNumTicketsAvailable(newNumOfTickets);
            }
        }
    } 




    
}