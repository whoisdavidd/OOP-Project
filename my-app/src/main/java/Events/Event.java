package Events;

public class Event {
    // add in an event id?
    private String eventName;
    private String venue;
    private String date; // In the form of DDMMYYYY (Can use Date() if u want)
    private String time; // In the form of 24h e.g. HHMM (2359) (i think shld have another function for this)
    private double ticketPrice;
    private int numOfTicketsAvail; 

    // constructor    

    public Event(String name, String venue, String date, String time, double ticketPrice, int numOfTicketsAvail){
        this.eventName = name;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.ticketPrice = ticketPrice;
        this.numOfTicketsAvail = numOfTicketsAvail;
    }
    
    // getter methods
    
    public String getEventName(){
        return this.eventName;
    }
    
    public String getEventVenue(){
        return this.venue;
    }
    public String getEventDate(){
        return this.date;
    }
    public String getEventTime(){
        return this.time;
    }
    public double getEventPrice(){
        return this.ticketPrice;
    }
    public int getEventAvailableTickets(){
        return this.numOfTicketsAvail;
    }

    // setter/update methods

    public void updateEventVenue(String newVenue){
        this.venue = newVenue;
    } 

    public void updateEventDate(String newDate){
        this.date = newDate;
    } 
    
    public void updateEventTime(String newTime){
        this.time = newTime;
    } 

    public void updateEventPrice(double newTicketPrice){
        this.ticketPrice = newTicketPrice;
    } 
    
    public void updateEventTicketNum(int newNumOfTickets){
        this.numOfTicketsAvail = newNumOfTickets;
    } 
}