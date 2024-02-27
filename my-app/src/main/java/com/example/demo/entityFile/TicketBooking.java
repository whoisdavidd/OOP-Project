package com.example.demo.entityFile;

import java.util.Date;
import java.util.Random;


public class TicketBooking extends Ticket {
    private Date bookingDate;
    private String bookingStatus;
    private String bookingNumber;

    // assuming that each customer has 1000 in the account
    private static final double original_balance = 1000.0;

    public TicketBooking(String eventSelection, int numberoftickets, double Paymentdetails, Date bookingDate,
            String bookingStatus, String bookingNumber) {
        super(eventSelection, numberoftickets, Paymentdetails);
        this.bookingDate = new Date(); // this one not sure must wait for the class first
        this.bookingStatus = null;
        this.bookingNumber = null;

    }

    public void BookingTicket(){
        if(checkTiming()){
            System.out.println("Please book another time");
            if(checkAvailability()){
                double ticketCost = super(ticketCost from sales); //waiting for sales
                double totalCost = numberoftickets * ticketCost;
                    if(totalCost <= Paymentdetails){
                        bookingNumber = generateBookingNumber();
                        //Send email hehehehe
                        SendEmail();
                        // Allow customers to view their booking history (not implemented here)
                        viewBookingHistory();
                    }else{
                        System.out.println("You broke bro");
                    }
            }else {
            System.out.println("Tickets not available for the selected event.");
            }
        }else {
            System.out.println("Booking time not within the allowed time frame.");
        }
}

    public boolean checkAvailability() {
        // need the date
        // I assume the ticket will be an ArrayList first so I just leave it here till i
        // know what is inside the arrayList
        return true;
    }

    public boolean checkTiming() {
        long currentTime = System.currentTimeMillis();
        // for the six months
        long SixBookingTime = currentTime + (180 * 24 * 60 * 60 * 1000L);
        long Sixhoursbefore = currentTime + (24 * 60 * 60 * 1000L);
        return bookingDate.getTime() >= Sixhoursbefore && bookingDate.getTime() <= SixBookingTime;
    }

    private void SendEmail(){
        //might need to create an email address lmao
        System.out.println("Confirmation email sent to " + super().getEmail()) //email
    }

    private void ViewHistory(){
        System.out.println("View booking history:" + super(eventSelection));
    }

    private String generateBookingNumber() {
        return "BK" + new Random().nextInt(1000);
    }

}
