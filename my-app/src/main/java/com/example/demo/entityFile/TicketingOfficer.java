package Users;

public class TicketingOfficer extends User {

    public TicketingOfficer(String username, String password) {
        super(username, password);
    }

    public boolean verifyTicketValidity(int ticketID) {
        // Complete based on how we verify
        //retrieve from database to verify if it exists
        return true; 
    }

    public boolean processTicketSales(int eventID, int numTickets) {
        // Use to process the ticket, must update the available ticket?
        // Event event = EventDAO.get(eventID)
        // if (event.getEventAvailableTickets() >= numTickets){
            
        // }
        return false; // or false
    }

    public void issueTickets(Customer customer, int eventID, int numTickets) {
        // create a booking record?
    }
}

