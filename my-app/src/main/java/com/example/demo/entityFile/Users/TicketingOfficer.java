package com.example.demo.entityFile.Users;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TICKETING_OFFICER")
public class TicketingOfficer extends User {

    public TicketingOfficer(){
        
    }

    
}

