// Import Hibernate-specific classes
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class JPA { //with hibernate

    public static void main(String[] args) {
        // Create a SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Create a Session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        try {
            // Your Hibernate operations go here
            List<JPAEntity> resultList = session.createQuery("from JPAEntity", JPAEntity.class).list();
            for (JPAEntity jpaEntity : resultList) {
                System.out.println(jpaEntity.getId() + " " + jpaEntity.getName() + " " + jpaEntity.getAge());
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle Hibernate exceptions
            transaction.rollback();
        } finally {
            // Close Session and SessionFactory
            session.close();
            sessionFactory.close();
        }
    
    }
}


