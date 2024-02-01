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


