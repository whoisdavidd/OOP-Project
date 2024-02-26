package com.example.demo.entityFile;

import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
