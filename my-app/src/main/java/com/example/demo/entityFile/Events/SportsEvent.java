package com.example.demo.entityFile.Events;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SPORTS_EVENT")
public class SportsEvent extends Event {
    
    @Column(name= "participants")
    @ElementCollection
    private List<String> participants; // ["Liverpool", "Manchester United"]

    @Column(name= "sport")
    private String sport;

    public SportsEvent(){

    }

    public List<String> getParticipants(){
        return this.participants;
    }

    public String getSport(){
        return this.sport;
    }

    public void setParticipants(List<String> newParticipants){
        this.participants = newParticipants;
    }

    public void setSport(String newSport){
        this.sport = newSport;
    }

    


}
