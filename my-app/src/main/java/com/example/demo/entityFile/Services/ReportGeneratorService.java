package com.example.demo.entityFile.Services;

import com.example.demo.entityFile.Events.Event;
import com.example.demo.entityFile.Ticketing.TicketingOption;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReportGeneratorService {
    
    public void generateTicketSalesReport(List<Event> events) {
        for (Event event : events) { 
            System.out.println("Event: " + event.getEventName());
            List<TicketingOption> ticketingOptionsList = event.getTicketingOptions();
            int totalTicketsSold = 0;
            for (TicketingOption to : ticketingOptionsList){
                totalTicketsSold += to.getNumTicketsSold();
            }
            String ticketsSold = String.valueOf(totalTicketsSold);
            System.out.println("Tickets Sold: " + ticketsSold);
            System.out.println("-----------------------------");
        }
    }
    //change revenue calc once updated
    public void generateRevenueReport(List<Event> events) {
        double totalRevenue = 0.0;
        for (Event event : events) {
            List<TicketingOption> ticketingOptionsList = event.getTicketingOptions();
            double eventRevenue = 0.0;
            for (TicketingOption to : ticketingOptionsList){
                eventRevenue += to.getTierRevenue();
            }
            totalRevenue += eventRevenue;
            System.out.println("Event: " + event.getEventName());
            System.out.println("Revenue: $" + eventRevenue);
            System.out.println("-----------------------------");
        }
        System.out.println("Total Revenue: $" + totalRevenue);
    }

    public byte[] exportToCSV(List<Event> events) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            // Writing header
            bos.write("Event Name, Tickets Sold, Revenue\n".getBytes(StandardCharsets.UTF_8));

            // Writing data
            for (Event event : events) {
                List<TicketingOption> ticketingOptionsList = event.getTicketingOptions();
                int ticketsSold = 0;
                double eventRevenue = 0.0;
                for (TicketingOption to : ticketingOptionsList){
                    ticketsSold += to.getNumTicketsSold();
                    eventRevenue += to.getTierRevenue();
                }

                String line = event.getEventName() + "," + ticketsSold + "," + eventRevenue + "\n";
                bos.write(line.getBytes(StandardCharsets.UTF_8));
            }

            return bos.toByteArray();

        } catch (IOException e) {
            System.err.println("Error generating CSV data: " + e.getMessage());
            return new byte[0];
        }
    }
}
