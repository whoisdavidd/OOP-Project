package com.mycompany.app;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;



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


                settings.put(Environment.SHOW_SQL,"true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
                settings.put(Environment.HBM2DDL_AUTO,"update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Student.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                SessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return SessionFactory;
    }
}