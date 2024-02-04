import Users.EventManager;

public class EventManagerTest {
    public static void main(String[] args) {
        EventManager em = new EventManager("jansen52x", "test123");
        em.addEvent("TestEvent", "SMU", "30/01/2024", "1400", 25.0, 10);
        System.out.println(em.getEvents());
    }
}
