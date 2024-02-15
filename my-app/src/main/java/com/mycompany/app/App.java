package com.mycompany.app;

import com.mycompany.app.User.UserEntity;
import com.mycompany.app.User.UserDao;



public class App 
{
    public static void main( String[] args )
    {
        UserDao UserDao = new UserDao();
        UserEntity user = new UserEntity("Rams","Pssword123");
        UserDao.saveUser(user);
        

    }
}
