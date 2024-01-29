import java.util.List;
import java.util.Arrays;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class EventManager extends User {
    // any variables

    public EventManager(String username, String password){
        super(username,password);
    }

    public addEvent(String eventName, String venue, String date, String time, 
    double ticketPrice, int numOfTicketsAvail){
        // File database = new File("../../../../database.txt");
        // FileWriter fw = new FileWriter(database);
        try{
            List<String> lines = Arrays.asList("Event Name: " + eventName, "Venue": + venue);
            Path file = Paths.get("../../../../database.txt");
            Files.write(file, lines, StandardCharsets.UTF_8);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    // public updateEventVenue(String eventName, String newVenue){

    // } 

    // public updateEventDate(String eventName, String newDate){

    // } 
    
    // public updateEventTime(String eventName, String newTime){

    // } 

    // public updateEventPrice(String eventName, double newTicketPrice){

    // } 
    
    // public updateEventTicketNum(String eventName, int newNumOfTickets){

    // } 




    
}
