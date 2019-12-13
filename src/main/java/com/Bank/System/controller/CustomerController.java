package com.Bank.System.controller;


import com.Bank.System.model.Customer;
import com.Bank.System.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void createOrUpdateCustomer(@RequestBody Customer customer) {
         customerService.createOrUpdateCustomer(customer);
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId ){
        return customerService.getCustomer(customerId);

    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId ){
         customerService.deleteCustomer(customerId);

    }

}
