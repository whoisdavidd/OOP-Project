package com.example.demo.entityFile.Events;
import java.util.List;

import jakarta.persistence.*;


@Entity
@DiscriminatorValue("CONCERT")
public class Concert extends Event {

    @ElementCollection
    private List<String> performers; // ["Taylor Swift", "John Smith"]

    public Concert(){

    }    
    
    public List<String> getPerformers(){
        return this.performers;
    }

    public void setPerformers(List<String> newPerformers){
        this.performers = newPerformers;
    }


}
