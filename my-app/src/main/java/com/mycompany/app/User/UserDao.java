package com.mycompany.app.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.app.Hibernate.HibernateUtil;
import com.mycompany.app.Hibernate.*;
import com.mycompany.app.User.*;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserInterface {
    // Save User
    // getAllUsers
    // getUserID
    // UpdateUserPassword
    // GetAllUsers
    // DeleteUsers

    // Create or Save new user
    public void saveUser(UserEntity user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UpdateUser method
    public void UpdateUser(UserEntity user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            session.saveOrUpdate(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    // GetUserID method
    public UserEntity GetUserId(long id) {
        Transaction transaction = null;
        UserEntity user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            user = session.get(UserEntity.class, id);
            // student = session.load(Student.class,id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    // GetAllUsers method
    @SuppressWarnings("unchecked")
    public List<UserEntity> getAllUsers() {
        Transaction transaction = null;
        List<UserEntity> user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            user = session.createQuery("from User").list();
            if (user == null) {
                user = new ArrayList<>(); // or any other appropriate default value
            }
            // student = session.load(Student.class,id)
            // commit the transacction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    // DeleteUser
    public void DeleteUser(long id) {
        Transaction transaction = null;
        UserEntity user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            user = session.get(UserEntity.class, id);
            session.delete(user);
            // student = session.load(Student.class,id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<UserEntity> GetAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetAllUsers'");
    }

}
