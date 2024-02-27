package Events;
import java.util.ArrayList;

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

    public Concert(ArrayList<String> performers, ArrayList<TicketingOption> ticketingOptions, 
    String eventName,  String eventVenue, String eventDate, String eventTime,
    double ticketPrice, int numOfTicketsAvail, double cancellationFee){ // with cancellation fee

        // not sure if Event provide eventID itself or need to give it.
        super(eventName, eventVenue, eventDate, eventTime, ticketPrice, numOfTicketsAvail, cancellationFee);
        this.performers = performers;
        this.ticketingOptions = ticketingOptions;

    }

    public Concert(ArrayList<String> performers, ArrayList<TicketingOption> ticketingOptions, 
    String eventName,  String eventVenue, String eventDate, String eventTime,
    double ticketPrice, int numOfTicketsAvail){ // without cancellation fee

        // not sure if Event provide eventID itself or need to give it.
        super(eventName, eventVenue, eventDate, eventTime, ticketPrice, numOfTicketsAvail);
        this.performers = performers;
        this.ticketingOptions = ticketingOptions;

    }

    public ArrayList<String> getPerformers(){
        return this.performers;
    }

    public ArrayList<TicketingOption> getTicketingOptions(){
        return this.ticketingOptions;
    }


}
