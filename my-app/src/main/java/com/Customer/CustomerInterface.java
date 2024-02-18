package com.Customer;


public interface CustomerInterface {
    void UpdateCustomer(Customer customer);
    double GetCustomerBalance(Customer customer);
    String GetCustomerEmail(Long id);
    Customer GetCustomerById(long id);
}
