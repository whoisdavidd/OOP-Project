package com.example.demo.repository;

import com.example.demo.entityFile.Events.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, Long> {
    
}