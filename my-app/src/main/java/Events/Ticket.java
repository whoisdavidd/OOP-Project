package Events;

import java.util.Date;

public class Ticket {
    private static int ticketCounter = 1;

    private int ticketID;
    private int eventID;
    private int customerID;
    private int numTickets;
    private int accompanyingGuests;
    private Date bookingDate;

    public Ticket(int eventID, int customerID, int numTickets, int accompanyingGuests) {
        this.ticketID = ticketCounter++;
        this.eventID = eventID;
        this.customerID = customerID;
        this.numTickets = numTickets;
        this.accompanyingGuests = accompanyingGuests;
        this.bookingDate = new Date(); // Current date and time when the ticket is booked
    }

    // Getters and setters for the attributes

    public int getTicketID() {
        return ticketID;
    }

    public int getEventID() {
        return eventID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public int getAccompanyingGuests() {
        return accompanyingGuests;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

}

