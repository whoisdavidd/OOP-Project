package com.example.demo.controller;

import java.util.List;

import org.springframework.http.MediaType;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entityFile.Events.Event;
import com.example.demo.entityFile.Services.ReportGeneratorService;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class ReportController {

    private final ReportGeneratorService reportGeneratorService;
    private final EventRepository eventRepository;

    @Autowired
    public ReportController(ReportGeneratorService reportGeneratorService, EventRepository eventRepository) {
        this.reportGeneratorService = reportGeneratorService;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/reports/export")
    public ResponseEntity<byte[]> exportReportToCSV() {
        String fileName = "event_report.csv";
        List<Event> events = eventRepository.findAll();
        byte[] data = reportGeneratorService.exportToCSV(events);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(header)
                .body(data);
    }
}
