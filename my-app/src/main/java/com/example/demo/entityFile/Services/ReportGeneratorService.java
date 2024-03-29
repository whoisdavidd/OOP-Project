package com.example.demo.entityFile.Services;

import com.example.demo.entityFile.Events.Event;
import com.example.demo.entityFile.Ticketing.TicketingOption;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

    public void exportToCSV(List<Event> events, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Writing header
            writer.append("Event Name, Tickets Sold, Revenue");
            writer.append("\n");

            // Writing data
            for (Event event : events) {
                List<TicketingOption> ticketingOptionsList = event.getTicketingOptions();
                int ticketsSold = 0;
                for (TicketingOption to : ticketingOptionsList){
                    ticketsSold += to.getNumTicketsSold();
                }
                double eventRevenue = 0.0;
                for (TicketingOption to : ticketingOptionsList){
                    eventRevenue += to.getTierRevenue();
                }

                writer.append(event.getEventName());
                writer.append(",");
                writer.append(String.valueOf(ticketsSold));
                writer.append(",");
                writer.append(String.valueOf(eventRevenue));
                writer.append("\n");
            }

            System.out.println("Data exported to csv successfully!");

        } catch (IOException e) {
            System.err.println("Error exporting data to csv: " + e.getMessage());
        }
    }
}
