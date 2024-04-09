package com.example.demo.entityFile.Services;

import com.example.demo.entityFile.Events.Event;
import com.example.demo.entityFile.Ticketing.Ticket;
import com.example.demo.entityFile.Ticketing.TicketingOption;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public byte[] exportToCSV(List<Event> events, List<Ticket> allTickets, Date startDate, Date endDate) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            // Initialize grand totals
            int grandTotalTicketsSold = 0;
            double grandTotalRevenue = 0.0;
            int grandTotalAttendees = 0;

            // Writing header
            String header = "Event Name, Tier Name, Tier Price, Tickets Sold Per Tier, Revenue Per Tier, Total Attendees, Attendance Percentage\n";
            bos.write(header.getBytes(StandardCharsets.UTF_8));
            bos.write("\n".getBytes(StandardCharsets.UTF_8)); // Add an empty line for spacing

            for (Event event : events) {
                List<Ticket> filteredTicketsForEvent = allTickets.stream()
                        .filter(t -> t.getEvent().equals(event) &&
                                (startDate == null || t.getBookingDate().after(startDate)) &&
                                (endDate == null || t.getBookingDate().before(endDate)))
                        .collect(Collectors.toList());

                int totalAttendeesForEvent = (int) filteredTicketsForEvent.stream().filter(Ticket::didAttend).count();
                int totalTicketsSoldForEvent = filteredTicketsForEvent.size();
                double totalRevenueForEvent = filteredTicketsForEvent.stream().mapToDouble(Ticket::getPrice).sum();

                // Update grand totals
                grandTotalTicketsSold += totalTicketsSoldForEvent;
                grandTotalAttendees += totalAttendeesForEvent;
                grandTotalRevenue += totalRevenueForEvent;

                // Write event tier data
                for (TicketingOption to : event.getTicketingOptions()) {
                    long ticketsSoldPerTier = filteredTicketsForEvent.stream()
                            .filter(t -> t.getTicketingOption().equals(to))
                            .count();
                    double revenuePerTier = ticketsSoldPerTier * to.getTierPrice();

                    String line = String.format("%s, %s, %.2f, %d, %.2f\n",
                            event.getEventName(),
                            to.getTierName(),
                            to.getTierPrice(),
                            ticketsSoldPerTier,
                            revenuePerTier
                    );
                    bos.write(line.getBytes(StandardCharsets.UTF_8));
                }

                // Write summary line for the event
                String eventSummary = String.format("--- %s, Total, , %d, %.2f, %d, %.2f%%\n",
                        event.getEventName(),
                        totalTicketsSoldForEvent,
                        totalRevenueForEvent,
                        totalAttendeesForEvent,
                        (totalTicketsSoldForEvent > 0) ? (100.0 * totalAttendeesForEvent / totalTicketsSoldForEvent) : 0.0
                );
                bos.write(eventSummary.getBytes(StandardCharsets.UTF_8));

                // Write a separator line between events
                bos.write("\n".getBytes(StandardCharsets.UTF_8));
            }

            // Write grand totals at the bottom of the CSV
            String grandTotalLine = String.format("=== Grand Total, , , %d, %.2f, %d, %.2f%%\n",
                    grandTotalTicketsSold,
                    grandTotalRevenue,
                    grandTotalAttendees,
                    (grandTotalTicketsSold > 0) ? (100.0 * grandTotalAttendees / grandTotalTicketsSold) : 0.0
            );
            bos.write("\n".getBytes(StandardCharsets.UTF_8)); // Separate grand total with a line
            bos.write(grandTotalLine.getBytes(StandardCharsets.UTF_8));

            return bos.toByteArray();
        } catch (IOException e) {
            System.err.println("Error generating CSV data: " + e.getMessage());
            return new byte[0];
        }
    }

}
   
