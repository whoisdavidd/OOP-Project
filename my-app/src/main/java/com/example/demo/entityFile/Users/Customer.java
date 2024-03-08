package com.example.demo.entityFile.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    
    @Id
    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;
    
    @Column(name= "Email_Address")
    private String EmailAddress;

    @Column(name= "Account_Balance")
    private float Account_Balance;
    

    public Customer() {

    }

    public Customer(String emailAddress, float account_Balance) {
        EmailAddress = emailAddress;
        Account_Balance = account_Balance;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public float getAccount_Balance() {
        return Account_Balance;
    }

    public void setAccount_Balance(float account_Balance) {
        Account_Balance = account_Balance;
    }
    

    
}
