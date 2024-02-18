package com.Customer;
import com.User.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends UserEntity{

    @Id
    @Column(name = "Id")
    private long id;

    @Column(name = "EmailAddress")
    private String emailAddress;

    @Column(name = "AccountBalance")
    private double accountBalance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Customer() {
    }

    public Customer(String emailAddress, double accountBalance, UserEntity user) {
        this.emailAddress = emailAddress;
        this.accountBalance = accountBalance;
        this.user = user;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}