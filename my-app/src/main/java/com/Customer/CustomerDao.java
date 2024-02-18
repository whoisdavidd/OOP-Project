package com.Customer;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Hibernate.HibernateUtil;



public class CustomerDao implements CustomerInterface {

    //GetCustomerById
    //UpdateCustomer
    //GetCustomerBalance
    //GetCustomerEmail

    public Customer GetCustomerById(long id) {
        Transaction transaction = null;
        Customer customer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save user
            customer = session.get(Customer.class, id);
            
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void UpdateCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            session.update(customer);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public double GetCustomerBalanced(Long id) {
        Transaction transaction = null;
        Customer customer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            customer = session.get(Customer.class,id );
            // student = session.load(Student.class,id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return customer != null ? customer.getAccountBalance(): 00;
    }
    public String GetCustomerEmail(Long id) {
        Transaction transaction = null;
        Customer customer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save user
            customer = session.get(Customer.class, id);
            
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return customer.getEmailAddress();
    }

    @Override
    public double GetCustomerBalance(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetCustomerBalance'");
    }



}
