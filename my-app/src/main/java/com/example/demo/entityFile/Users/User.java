package com.example.demo.entityFile.Users;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "user")
public class User {

    @Id
    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;

    @Column(name = "user_type", insertable =  false, updatable = false)
    protected String userType;

    // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // @PrimaryKeyJoinColumn
    // private Customer customer;

    // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // @PrimaryKeyJoinColumn
    // private EventManager eventManager;

    // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // @PrimaryKeyJoinColumn
    // private TicketingOfficer ticketingOfficer;

    public User() {

    }
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public String getUserType(){
        return userType;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}
