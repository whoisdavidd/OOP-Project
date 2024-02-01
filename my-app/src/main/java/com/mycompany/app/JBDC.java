package com.mycompany.app;

import java.sql.*;

public class JBDC {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        //insert password here
        String password = "";
        try{

            Connection connection = DriverManager.getConnection(url,username,password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("Select * from student");

            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+ resultSet.getInt(3));
            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }  
    }
    //  // Create a new record
    //  static void createRecord(String name, int age) {
    //     try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
    //         String insertQuery = "INSERT INTO student (name, age) VALUES (?, ?)";
    //         try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
    //             preparedStatement.setString(1, name);
    //             preparedStatement.setInt(2, age);
    //             int affectedRows = preparedStatement.executeUpdate();
    //             System.out.println("Record created, affected rows: " + affectedRows);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // // Read all records
    // static void readRecords() {
    //     try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
    //         Statement statement = connection.createStatement();
    //         ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

    //         while (resultSet.next()) {
    //             System.out.println(resultSet.getInt("id") + " " +
    //                     resultSet.getString("name") + " " +
    //                     resultSet.getInt("age"));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // // Update a record
    // static void updateRecord(int id, String name, int age) {
    //     try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
    //         String updateQuery = "UPDATE student SET name=?, age=? WHERE id=?";
    //         try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
    //             preparedStatement.setString(1, name);
    //             preparedStatement.setInt(2, age);
    //             preparedStatement.setInt(3, id);
    //             int affectedRows = preparedStatement.executeUpdate();
    //             System.out.println("Record updated, affected rows: " + affectedRows);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // // Delete a record
    // static void deleteRecord(int id) {
    //     try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
    //         String deleteQuery = "DELETE FROM student WHERE id=?";
    //         try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
    //             preparedStatement.setInt(1, id);
    //             int affectedRows = preparedStatement.executeUpdate();
    //             System.out.println("Record deleted, affected rows: " + affectedRows);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
//     public class JDBCExample {
//         // Database connection parameters
//         static final String URL = "jdbc:mysql://localhost:3306/jdbc";
//         static final String USERNAME = "root";
//         static final String PASSWORD = "";
    
//         public static void main(String[] args) {
//             // Example CRUD operations
//             createRecord("John Doe", 25);
//             readRecords();
//             updateRecord(1, "Updated Name", 30);
//             readRecords();
//             deleteRecord(1);
//             readRecords();
//         }
// }
}

// import javax.persistence.*;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;

// public class JpaWithJdbcExample {

//     public static void main(String[] args) {
//         // JPA: Create an EntityManagerFactory
//         EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePU");

//         // JPA: Create an EntityManager
//         EntityManager entityManager = entityManagerFactory.createEntityManager();

//         // JPA: Begin a transaction
//         EntityTransaction transaction = entityManager.getTransaction();
//         transaction.begin();

//         try {
//             // JPA: Create a new Student entity
//             Student student = new Student();
//             student.setName("John Doe");
//             student.setAge(20);

//             // JPA: Persist the entity to the database
//             entityManager.persist(student);

//             // JDBC: Use a separate JDBC connection to perform low-level operations
//             try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password")) {
//                 // JDBC: Prepare a statement to execute a query
//                 String insertQuery = "INSERT INTO student (name, age) VALUES (?, ?)";
//                 try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
//                     // JDBC: Set parameters and execute the query
//                     preparedStatement.setString(1, student.getName());
//                     preparedStatement.setInt(2, student.getAge());
//                     preparedStatement.executeUpdate();
//                 }
//             } catch (SQLException e) {
//                 e.printStackTrace();
//                 // Handle JDBC exceptions
//             }

//             // JPA: Commit the transaction
//             transaction.commit();
//         } catch (Exception e) {
//             e.printStackTrace();
//             // Handle JPA exceptions
//             transaction.rollback();
//         } finally {
//             // JPA: Close the EntityManager and EntityManagerFactory
//             entityManager.close();
//             entityManagerFactory.close();
//         }
//     }
// }

