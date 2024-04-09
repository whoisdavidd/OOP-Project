package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entityFile.Events.Event;
import com.example.demo.entityFile.Services.ReportGeneratorService;
import com.example.demo.entityFile.Ticketing.Ticket;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.TicketRepository;

@RestController
public class ReportController {

    private final ReportGeneratorService reportGeneratorService;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public ReportController(ReportGeneratorService reportGeneratorService, EventRepository eventRepository, TicketRepository ticketRepository) {
        this.reportGeneratorService = reportGeneratorService;
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/reports/export")
    public ResponseEntity<byte[]> exportReportToCSV(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        String fileName = "event_report.csv";
        List<Event> events = eventRepository.findAll();
        List<Ticket> tickets = ticketRepository.findAll();
        byte[] data = reportGeneratorService.exportToCSV(events, tickets, startDate, endDate);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(header)
                .body(data);
    }
}

