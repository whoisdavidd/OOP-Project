    package com.example.demo.controller;

    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import com.example.demo.entityFile.Users.TicketingOfficer;
    import com.example.demo.exception.*;
    import com.example.demo.repository.*;

    @RestController
    @RequestMapping("/api/ticketingOfficer")
    public class TicketingOfficerController {
        @Autowired //automatically inject an instance of customer repository
        private TicketingOfficerRepository ticketingOfficerRepository;

        @PostMapping //works
        public TicketingOfficer createTicketingOfficer(@RequestBody TicketingOfficer ticketingOfficer) {
            TicketingOfficer savedTicketingOfficer = this.ticketingOfficerRepository.save(ticketingOfficer);
            this.ticketingOfficerRepository.save(ticketingOfficer);
            return savedTicketingOfficer;
        }

        // no put mapping as TO has no unique variables

        // @PutMapping("/{username}")
        // public TicketingOfficer updateTicketingOfficer(@RequestBody TicketingOfficer TicketingOfficer, @PathVariable("username") String username) {
        //     TicketingOfficer existingUser = this.TicketingOfficerRepository.findById(username)
        //             .orElseThrow(() -> new ResourceNotFoundException("User not found with username :" + username));
        //     return this.TicketingOfficerRepository.save(existingUser);
        // }

        @GetMapping //works
        public List<TicketingOfficer> getAllTicketingOfficers() {
            return this.ticketingOfficerRepository.findAll();
        }

        // get user by username
        @GetMapping("/{username}") //works
        public TicketingOfficer getTicketingOfficerById(@PathVariable(value = "username") String username) {
            return this.ticketingOfficerRepository.findById(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with username :" + username));
        }
    }
