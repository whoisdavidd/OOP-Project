package Services;

import Events.Event;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {
    
    public void generateTicketSalesReport(List<Event> events) {
        // Logic to generate ticket sales report
        for (Event event : events) {
            System.out.println("Event: " + event.getEventName());
            System.out.println("Tickets Sold: " + (event.getEventTotalTickets() - event.getEventAvailableTickets()));
            System.out.println("-----------------------------");
        }
    }

    public void generateRevenueReport(List<Event> events) {
        // Logic to generate revenue report
        double totalRevenue = 0.0;
        for (Event event : events) {
            double eventRevenue = (event.getEventTotalTickets() - event.getEventAvailableTickets()) * event.getTicketPrice();
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
                int ticketsSold = event.getEventTotalTickets() - event.getEventAvailableTickets();
                double revenue = ticketsSold * event.getTicketPrice();

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
