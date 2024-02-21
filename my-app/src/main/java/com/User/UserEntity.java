package com.User;


import javax.persistence.*;

import com.Customer.Customer;



@Entity
@Table(name = "user") // add table annotation
// java class
public class UserEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long Id;
    @Column(name = "Username")
    private String Username;
    @Column(name = "Password")
    private String Password;
    @Version
    private int version; // Add the @Version annotation here

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public UserEntity() {
    }


    public UserEntity(String Username, String Password) {

        this.Username = Username;
        this.Password = Password;
       
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

}
