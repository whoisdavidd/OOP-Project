package com.example.demo.entityFile.Users;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User{
    
    @Column(name= "emailAddress")
    private String emailAddress;

    @Column(name= "accountBalance")
    private float accountBalance;
    

    public Customer() {

    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public float getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }
    

    
}
