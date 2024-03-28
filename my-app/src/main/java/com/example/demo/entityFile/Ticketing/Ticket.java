<<<<<<<< HEAD:my-app/src/main/java/com/example/demo/entityFile/Events/Ticket.java
package com.example.demo.entityFile.Events;

========
package com.example.demo.entityFile.Ticketing;
>>>>>>>> yr_branch:my-app/src/main/java/com/example/demo/entityFile/Ticketing/Ticket.java

import java.util.Date;

public class Ticket {
    private static int ticketCounter = 1;

    private int ticketID;
    private int eventID;
    private int customerID;
    private Date bookingDate;

    public Ticket(int eventID, int customerID) {
        this.ticketID = ticketCounter++;
        this.eventID = eventID;
        this.customerID = customerID;
        this.bookingDate = new Date(); // Current date and time when the ticket is booked
    }

    public Ticket(int ticketID, int eventID, int customerID, Date bookingDate){
        this.ticketID = ticketID;
        this.eventID = eventID;
        this.customerID = customerID;
        this.bookingDate = bookingDate;
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

    public Date getBookingDate() {
        return bookingDate;
    }

}

