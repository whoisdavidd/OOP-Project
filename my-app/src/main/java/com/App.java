package com;


import com.User.UserEntity;
import com.Customer.Customer;
import com.Customer.CustomerDao;
import com.User.UserDao;




public class App 
{
    public static void main( String[] args )
    {
        UserDao UserDao = new UserDao();
        UserEntity user = new UserEntity("Rams","Pssword123");
        UserDao.saveUser(user);
        // CustomerDao customerDao = new CustomerDao();
        // Customer customer = new Customer("rams@example.com", 100.0, user);
        // customerDao.UpdateCustomer(customer);

        // Customer savedCustomer = customerDao.GetCustomerById(customer.getId());
        // if (savedCustomer != null) {
        //     System.out.println("Customer successfully saved and retrieved:");
        //     System.out.println("Email Address: " + savedCustomer.getEmailAddress());
        //     System.out.println("Account Balance: " + savedCustomer.getAccountBalance());
        //     System.out.println("Associated User: " + savedCustomer.getUser().getUsername());
        // } else {
        //     System.out.println("Failed to retrieve the saved Customer.");
        // }
    }
}
