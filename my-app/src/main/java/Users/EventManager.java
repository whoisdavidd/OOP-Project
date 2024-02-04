package Users;

import java.util.ArrayList;
import Events.Event;

public class EventManager extends User {
    // variables
    ArrayList<Event> eventList = new ArrayList<>();

    // constructor
    
    public EventManager(String username, String password){
        super(username,password);
        // get from database to populate eventList?
    }

    // adding events method

    public void addEvent(String eventName, String venue, String date, String time, 
    double ticketPrice, int numOfTicketsAvail){
        // add to database ????
        Event e = new Event(eventName, venue, date, time, ticketPrice, numOfTicketsAvail); // creates new Event object
        this.eventList.add(e); // adds Event object to ArrayList of events.
    }

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
                this.eventList.get(i).updateEventPrice(newTicketPrice);
            }
        }
    } 
    
    public void updateEventTicketNum(String eventName, int newNumOfTickets){
        for (int i=0; i<this.eventList.size();i++){
            if (this.eventList.get(i).getEventName().equals(eventName)){
                this.eventList.get(i).updateEventTicketNum(newNumOfTickets);
            }
        }
    } 




    
}
