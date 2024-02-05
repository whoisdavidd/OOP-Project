package com.mycompany.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IStudentDao {
    // saveStudent
    // getAllStudents
    // getStudentById
    // UpdateStudent
    // DeleteStudent

    // save entity
    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            session.save(student);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    // update
    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            session.saveOrUpdate(student);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    // get student
    public Student getStudentById(long id) {
        Transaction transaction = null;
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            student = session.get(Student.class, id);
            // student = session.load(Student.class,id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return student;
    }

    @SuppressWarnings("unchecked")
    public List<Student> getAllStudents() {
        Transaction transaction = null;
        List<Student> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            students = session.createQuery("from Student").list();
            if (students == null) {
                students = new ArrayList<>(); // or any other appropriate default value
            }
            // student = session.load(Student.class,id)
            // commit the transacction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return students;
    }

    // get student
    public void deleteStudent(long id) {
        Transaction transaction = null;
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student
            student = session.get(Student.class, id);
            session.delete(student);
            // student = session.load(Student.class,id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Student getStudyByID(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudyByID'");
    }

}
