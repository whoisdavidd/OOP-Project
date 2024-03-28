package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entityFile.Events.Movie;
import com.example.demo.repository.*;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired //automatically inject an instance of customer repository
    private MovieRepository movieRepository;

    @PostMapping // works
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = this.movieRepository.save(movie);
        return ResponseEntity.ok(savedMovie);
    }
    @PutMapping("/{eventID}") 
    public ResponseEntity<?> setMovie(@RequestBody Movie movie, @PathVariable("eventID") Long eventID) {
        if (!this.movieRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        Movie existingMovie = this.movieRepository.findById(eventID).get();
        existingMovie.setEventName(movie.getEventName());
        existingMovie.setEventVenue(movie.getEventVenue());
        existingMovie.setEventDate(movie.getEventDate());
        existingMovie.setEventTime(movie.getEventTime());
        existingMovie.setCancellationFee(movie.getCancellationFee());
        existingMovie.setRating(movie.getRating());
        existingMovie.setDuration(movie.getDuration());
        existingMovie.setMainCast(movie.getMainCast());
        this.movieRepository.save(existingMovie);
        return ResponseEntity.ok(existingMovie);
    }
    @GetMapping 
    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    // get event by eventID
    @GetMapping("/{eventID}")
    public ResponseEntity<?> getMovieById(@PathVariable(value = "eventID") Long eventID) {
        if (!this.movieRepository.findById(eventID).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event not found");
        }
        Movie existingMovie = this.movieRepository.findById(eventID).get();
        return ResponseEntity.ok(existingMovie);
    }
}
