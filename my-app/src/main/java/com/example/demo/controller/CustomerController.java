    package com.example.demo.controller;

    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.PutMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import com.example.demo.entityFile.Users.Customer;
    import com.example.demo.exception.*;
    import com.example.demo.repository.*;

    @RestController
    @RequestMapping("/api/customer")
    public class CustomerController {
        @Autowired //automatically inject an instance of customer repository
        private CustomerRepository customerRepository;

        @PostMapping // works
        public Customer createCustomer(@RequestBody Customer customer) {
            customer.setAccountBalance(5000);
            Customer savedCustomer = this.customerRepository.save(customer);
            this.customerRepository.save(customer);
            return savedCustomer;
        }
        @PutMapping("/{username}") // works but can only update email and accountbalance, not username as well
        public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("username") String username) {
            Customer existingCustomer = this.customerRepository.findById(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with username :" + username));
            existingCustomer.setEmailAddress(customer.getEmailAddress());
            existingCustomer.setAccountBalance(customer.getAccountBalance());
            existingCustomer.setPassword(customer.getPassword());

            return this.customerRepository.save(existingCustomer);
        }
        @GetMapping 
        public List<Customer> getAllCustomers() {
            return this.customerRepository.findAll();
        }

        // get user by username
        @GetMapping("/{username}")
        public Customer getCustomerById(@PathVariable(value = "username") String username) {
            return this.customerRepository.findById(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with username :" + username));
        }
    }
