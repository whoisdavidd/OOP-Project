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

    import com.example.demo.entityFile.*;
    import com.example.demo.entityFile.Users.Customer;
    import com.example.demo.exception.*;
    import com.example.demo.repository.*;

    @RestController
    @RequestMapping("/api/customer")
    public class CustomerController {
        @Autowired //automatically inject an instance of customer repository
        private CustomerRepository customerRepository;

        @PostMapping
        public Customer createCustomer(@RequestBody Customer customer) {
            Customer savedCustomer = this.customerRepository.save(customer);
            this.customerRepository.save(customer);
            return savedCustomer;
        }
        @PutMapping("/{id}")
        public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("id") long userId) {
            Customer existingUser = this.customerRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
            existingUser.setEmailAddress(customer.getEmailAddress());
            existingUser.setAccount_Balance(customer.getAccount_Balance());

            return this.customerRepository.save(existingUser);
        }
        @GetMapping
        public List<Customer> getAllCustomers() {
            return this.customerRepository.findAll();
        }

        // get user by id
        @GetMapping("/{id}")
        public Customer getCustomerById(@PathVariable(value = "id") long userId) {
            return this.customerRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        }

        @PostMapping("/saveCustomer")
        public Customer saveCustomer(@RequestBody Customer customer) {
            System.out.println("Customer save called...");
            Customer outCustomer = customerRepository.save(customer);
            System.out.println("Saved Customer :: " + outCustomer);
            return outCustomer;
        }
    }
