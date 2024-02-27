package Services;

import Users.Customer;
import Events.*;

public class BookingService {

    public Ticket bookTicket(Customer customer, int eventID, int numTickets) {
        // Process the booking of ticket
        //
        return null; // Newly booked ticket
    }

    public boolean cancelBooking(int ticketID) {
        // Refund amount to the customer, update ticket availability, etc.
        //verify time details
        return true; // or false
    }

    public void generateConfirmationEmail(Ticket ticket) {
        // Use ticket details to compose and send the email
    }

}
