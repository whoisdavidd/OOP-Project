package com.example.demo.entityFile.Users;

import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "USERNAME")
    private String username;


    @Column(name = "PASSWORD")
    private String password;


    @OneToOne(mappedBy = "user")
    private Customer customer;

    public User() {

    }
    public User(String username, String password) {
        super();
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
