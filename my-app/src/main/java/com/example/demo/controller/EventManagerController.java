    package com.example.demo.controller;

    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import jakarta.validation.*;

    import com.example.demo.entityFile.Users.EventManager;
    import com.example.demo.exception.*;
    import com.example.demo.repository.*;

    @RestController
    @RequestMapping("/api/eventManager")
    public class EventManagerController {
        @Autowired //automatically inject an instance of customer repository
        private EventManagerRepository eventManagerRepository;

        @PostMapping //works
        public EventManager createEventManager(@Valid @RequestBody EventManager eventManager) {
            EventManager savedEventManager = this.eventManagerRepository.save(eventManager);
            this.eventManagerRepository.save(eventManager);
            return savedEventManager;
        }

        // no put mapping as EM has no unique variables

        // @PutMapping("/{username}")
        // public EventManager updateEventManager(@Valid @RequestBody EventManager eventManager, @PathVariable("username") String username) {
        //     EventManager existingUser = this.eventManagerRepository.findById(username)
        //             .orElseThrow(() -> new ResourceNotFoundException("User not found with username :" + username));
        //     return this.eventManagerRepository.save(existingUser);
        // }

        @GetMapping //works
        public List<EventManager> getAllEventManagers() {
            return this.eventManagerRepository.findAll();
        }

        // get user by username
        @GetMapping("/{username}") //works
        public EventManager getEventManagerById(@PathVariable(value = "username") String username) {
            return this.eventManagerRepository.findById(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with username :" + username));
        }
    }
