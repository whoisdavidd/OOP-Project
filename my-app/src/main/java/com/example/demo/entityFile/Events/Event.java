package Events;

public class Event {
    private int eventID;
    private String eventName;
    private String venue;
    private String date; // In the form of DDMMYYYY
    private String time; // In the form of 24h e.g. HHMM (2359)
    private double ticketPrice;
    private int numOfTicketsAvail; 
    private double cancellationFee;

    // constructor

    public Event(String name, String venue, String date, 
    String time, double ticketPrice, int numOfTicketsAvail){ // without cancellation fee
        
        // Automatically create eventID based on latest eventID?
        this.eventName = name;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.ticketPrice = ticketPrice;
        this.numOfTicketsAvail = numOfTicketsAvail;

    }

    public Event(String name, String venue, String date, 
    String time, double ticketPrice, int numOfTicketsAvail, double cancellationFee){ // with cancellation fee

        // Automatically create eventID based on latest eventID?
        this.eventName = name;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.ticketPrice = ticketPrice;
        this.numOfTicketsAvail = numOfTicketsAvail;
        this.cancellationFee = cancellationFee;

    }
    
    // getter methods
    
    public int getEventID(){
        return this.eventID;
    }
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
    public double getCancellationFee(){
        return this.cancellationFee;
    }

    // setter/update methods

    // dont see the need for update event id

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

    public void updateCancellationFee(double newCancellationFee){
        this.cancellationFee = newCancellationFee;
    }
}