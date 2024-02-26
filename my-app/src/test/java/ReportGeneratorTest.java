import Events.Event;
import Users.EventManager;
import Users.Customer;
import Services.BookingService;
import Services.ReportGeneratorService;
import java.util.ArrayList;
import java.util.List;

public class ReportGeneratorTest {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager("event_manager", "password");

        eventManager.addEvent("Concert", "City Hall", "2024-02-10", "19:00", 50.0, 100);
        eventManager.addEvent("Conference", "Convention Center", "2024-03-15", "09:00", 30.0, 150);

        Customer customer1 = new Customer("john_doe", "john@example.com", "password123");
        Customer customer2 = new Customer("jane_smith", "jane@example.com", "pass456");

        BookingService bookingService = new BookingService();

        bookingService.bookTicket(customer1, 1, 2);
        bookingService.bookTicket(customer2, 2, 3);

        ReportGeneratorService reportGenerator = new ReportGeneratorService();

        List<Event> events = eventManager.getEvents();
        reportGenerator.generateTicketSalesReport(events);
        reportGenerator.generateRevenueReport(events);
        System.out.println("hello????????");

        reportGenerator.exportToCSV(events, "event_data.csv");
    }
}
