package com.Customer;

import com.User.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String Password;

    @Column(name = "EmailAddress")
    private String emailAddress;

    @Column(name = "AccountBalance")
    private double accountBalance;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    
    private UserEntity user;

    public Customer() {
        // Default constructor required by JPA
    }

    public Customer(long id, String username, String password, String emailAddress, double accountBalance) {
        this.id = id;
        this.username = username;
        this.Password  =password;
        this.emailAddress = emailAddress;
        this.accountBalance = accountBalance;
    }

    // Getters and setters


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }


  
}