package com.example.demo.entityFile.Events;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOVIE")
public class Movie extends Event {

    @Column(name= "mainCast")
    @ElementCollection
    private List<String> mainCast; // ["Taylor Swift", "John Smith"]
    
    @Column(name= "rating")
    private String rating; // "PG13"
    
    @Column(name= "duration")
    private int duration; // in minutes, to nearest minute e.g. 180

    public Movie(){

    }

    public List<String> getMainCast(){
        return this.mainCast;
    }

    public String getRating(){
        return this.rating;
    }

    public int getDuration(){
        return this.duration;
    }

    public void setMainCast(List<String> newMainCast){
        this.mainCast = newMainCast;
    }

    public void setRating(String newRating){
        this.rating = newRating;
    }

    public void setDuration(int newDuration){
        this.duration = newDuration;
    }

}
