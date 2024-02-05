package com.mycompany.app;

import java.util.List;

public interface IStudentDao {
    void saveStudent(Student student);
    void updateStudent(Student student);
    Student getStudyByID(long id);
    List<Student> getAllStudents();
    void deleteStudent(long id);
}
