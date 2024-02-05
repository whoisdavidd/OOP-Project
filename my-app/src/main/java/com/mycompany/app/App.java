package com.mycompany.app;


public class App 
{
    public static void main( String[] args )
    {
        StudentDao studentDao = new StudentDao();
        Student student = new Student("Ram","adsa","yoruma@gmail");
        studentDao.saveStudent(student);
        

    }
}
