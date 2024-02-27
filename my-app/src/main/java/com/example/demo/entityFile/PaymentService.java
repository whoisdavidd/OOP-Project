package Services;


public class PaymentService {
    public boolean processPayment(int customerID, double amount) {
        // Deduct amount from customer account.
        return true; // or false
    }

    public boolean processRefund(int customerID, double amount) {
        // Add amount to customer account.
        return true; // or false
    }

}
