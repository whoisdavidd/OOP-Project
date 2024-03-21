package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.*;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public boolean isEventIDValid(long eventID) {
        if (eventID > 0){
            return !eventRepository.findById(eventID).isPresent();
        }
        return false;
    }

    // Other methods for user management...

}