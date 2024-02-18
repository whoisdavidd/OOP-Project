package com.User;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

import com.Customer.Customer;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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


    @OneToOne(mappedBy = "user")
    private Customer customer;

    public UserEntity() {
    }


    public UserEntity(String Username, String Password) {
        super();
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
