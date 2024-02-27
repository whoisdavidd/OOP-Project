package com.example.demo.entityFile;

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
public class Customer  {
    
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "USERNAME")
    private String username;


    @Column(name = "PASSWORD")
    private String password;

    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //reference more foreign key can be many
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    
    @Column(name= "Email_Address")
    private String EmailAddress;

    @Column(name= "Account_Balance")
    private float Account_Balance;

    public Customer(String emailAddress, float account_Balance) {
        EmailAddress = emailAddress;
        Account_Balance = account_Balance;
    }
    

    public Customer() {
        //TODO Auto-generated constructor stub
    }



    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
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
    public void setUser(User user) {
        this.user = user;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    
}
