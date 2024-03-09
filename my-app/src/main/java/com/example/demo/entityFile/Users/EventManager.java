package com.example.demo.entityFile.Users;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EVENT_MANAGER")
public class EventManager extends User{
    // variables
    @Id
    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;



    // constructor
    
    public EventManager(){

    }

    




    
}