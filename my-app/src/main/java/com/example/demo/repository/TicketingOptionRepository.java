package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entityFile.Ticketing.TicketingOption;

@Repository
public interface TicketingOptionRepository extends JpaRepository<TicketingOption, Long> {


    
}
