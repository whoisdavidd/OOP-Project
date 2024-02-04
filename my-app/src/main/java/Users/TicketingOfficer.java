package Users;
import Events.*;

public class TicketingOfficer extends User {

    public TicketingOfficer(String username, String password) {
        super(username, password);
    }

    public boolean verifyTicketValidity(Ticket ticket) {
        // Complete based on how we verify
        return true; 
    }

    public boolean processTicketSales(int eventID, int numTickets) {
        // Use to process the ticket, must update the available ticket?
        return true; // or false
    }

    public void issueTickets(Customer customer, int eventID, int numTickets) {
        // create a booking record?
    }
}

