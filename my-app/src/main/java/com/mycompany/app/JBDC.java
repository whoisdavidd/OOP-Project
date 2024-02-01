package com.mycompany.app;

import java.sql.*;

public class JBDC {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        //insert password here
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

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
}
