package com.example.demo.entityFile.Services;

import com.example.demo.entityFile.Users.Customer;

public class PaymentService {
    public boolean processPayment(Customer customer, double amount) {
        // Deduct amount from customer account.
        return true; // or false
    }

    public boolean processRefund(Customer customer, double amount) {
        // Add amount to customer account.
        return true; // or false
    }

}
