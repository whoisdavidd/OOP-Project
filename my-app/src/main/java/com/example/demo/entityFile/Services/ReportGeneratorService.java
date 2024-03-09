package com.example.demo.entityFile.Services;

import com.example.demo.entityFile.Events.Event;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGeneratorService {
    
    public void generateTicketSalesReport(List<Event> events) {
        for (Event event : events) { 
            System.out.println("Event: " + event.getEventName());
            System.out.println("Tickets Sold: " + (event.getEventAvailableTickets() - event.getEventAvailableTickets()));
            System.out.println("-----------------------------");
        }
    }
    //change revenue calc once updated
    public void generateRevenueReport(List<Event> events) {
        double totalRevenue = 0.0;
        for (Event event : events) {
            double eventRevenue = (event.getEventAvailableTickets() - event.getEventAvailableTickets()) * event.getEventPrice();
            totalRevenue += eventRevenue;
            System.out.println("Event: " + event.getEventName());
            System.out.println("Revenue: $" + eventRevenue);
            System.out.println("-----------------------------");
        }
        System.out.println("Total Revenue: $" + totalRevenue);
    }

    public void exportToCSV(List<Event> events, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Writing header
            writer.append("Event Name, Tickets Sold, Revenue");
            writer.append("\n");

            // Writing data
            for (Event event : events) {
                int ticketsSold = event.getEventAvailableTickets() - event.getEventAvailableTickets();
                double revenue = ticketsSold * event.getEventPrice();

                writer.append(event.getEventName());
                writer.append(",");
                writer.append(String.valueOf(ticketsSold));
                writer.append(",");
                writer.append(String.valueOf(revenue));
                writer.append("\n");
            }

            System.out.println("Data exported to csv successfully!");

        } catch (IOException e) {
            System.err.println("Error exporting data to csv: " + e.getMessage());
        }
    }
}
