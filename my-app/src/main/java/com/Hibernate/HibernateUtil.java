package com.Hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.DatabaseMetaData;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.Customer.Customer;
import com.User.*;
import com.mysql.cj.Session;


//template to create/update
public class HibernateUtil{
    private static SessionFactory SessionFactory;

    public static SessionFactory getSessionFactory(){
        if(SessionFactory == null){
            try{
                //read 
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL,"jdbc:mysql://localhost:3306/jdbc");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                // If the table exists, set HBM2DDL_AUTO to "update"; otherwise, set it to "create"
                settings.put(Environment.HBM2DDL_AUTO,  "update");

                settings.put(Environment.SHOW_SQL,"true");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(UserEntity.class);
                configuration.addAnnotatedClass(Customer.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                SessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return SessionFactory;
    }
    // public static boolean checkIfTableExists(String tableName, Properties settings) {
    //     try (Connection connection = java.sql.DriverManager.getConnection(
    //             settings.getProperty(Environment.URL),
    //             settings.getProperty(Environment.USER),
    //             settings.getProperty(Environment.PASS)
    //     )) {
    //         DatabaseMetaData metaData = connection.getMetaData();
    //         ResultSet tables = metaData.getTables(null,null, tableName, null);
    //         return tables.next();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    
    // }
}

