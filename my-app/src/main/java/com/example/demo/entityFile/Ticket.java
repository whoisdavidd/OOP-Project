package com.example.demo.entityFile;

public class Ticket {
    private String eventSelection;
    private int numberoftickets;
    private double Paymentdetails;

    public Ticket(String eventSelection, int numberoftickets, double Paymentdetails){
        this.eventSelection = eventSelection;
        this.numberoftickets = numberoftickets;
        this.Paymentdetails = Paymentdetails;
    }

    public String getEventSelection() {
        return eventSelection;
    }

    public void setEventSelection(String eventSelection) {
        this.eventSelection = eventSelection;
    }

    public int getNumberoftickets() {
        return numberoftickets;
    }

    public void setNumberoftickets(int numberoftickets) {
        this.numberoftickets = numberoftickets;
    }

    public double getPaymentdetails() {
        return Paymentdetails;
    }

    public void setPaymentdetails(double paymentdetails) {
        Paymentdetails = paymentdetails;
    }
}
