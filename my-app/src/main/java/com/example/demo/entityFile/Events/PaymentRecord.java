package com.example.demo.entityFile.Events;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class PaymentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String chargeId; // For storing Charge ID or PaymentIntent ID
    private BigDecimal amount;
    private String CustomerId; // Optional, for reference
    private LocalDateTime paymentDate;
    private String eventID;
    private int numTickets;

    // Getters and setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChargeId() {
        return this.chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCustomerId() {
        return this.CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public LocalDateTime getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getEventID() {
        return this.eventID;
    }
    public void setEventID(String eventID){
        this.eventID = eventID;
    }
    public int getNumTickets(){
        return this.numTickets;
    }
    public void setNumTickets(int numTickets){
        this.numTickets = numTickets;
    }
}

