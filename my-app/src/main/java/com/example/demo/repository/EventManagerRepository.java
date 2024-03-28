package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entityFile.Users.EventManager;

@Repository
public interface EventManagerRepository extends JpaRepository<EventManager, String> {


    
}
