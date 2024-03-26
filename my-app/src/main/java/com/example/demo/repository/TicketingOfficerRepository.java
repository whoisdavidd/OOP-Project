package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entityFile.Users.TicketingOfficer;

@Repository
public interface TicketingOfficerRepository extends JpaRepository<TicketingOfficer, String> {


    
}
