package com.example.demo.entityFile.Users;

import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private EventManager eventManager;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private TicketingOfficer ticketingOfficer;

    public User() {

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;

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
    public void setPassword(String password) {
        this.password = password;
    }

}
