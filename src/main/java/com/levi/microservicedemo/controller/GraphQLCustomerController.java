package com.levi.microservicedemo.controller;

import com.levi.microservicedemo.domain.Customer;
import com.levi.microservicedemo.domain.CustomerContactDetails;
import com.levi.microservicedemo.service.CustomerContactDetailsService;
import com.levi.microservicedemo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class GraphQLCustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerContactDetailsService customerContactDetailsService;

    /*@GetMapping("/customers/{name}")
    public List<Customer> getCustomerByName(@PathVariable String name) {
        log.info("Getting customer by name {} ", name);
        List customerList = customerService.getCustomerByName(name);
        log.info("Received {} customers by name {}", customerList.size(), name);
        return customerList;
    }*/

    @QueryMapping
    public Customer customerById(@Argument String id) {
        log.info("Quering customer in GraphQL Server by id {}", id);
        Customer customer = customerService.getCustomerById(id);
        return customer;
    }

    @SchemaMapping
    public CustomerContactDetails customerContactDetails(Customer customer) {
        log.info("Quering the Customer Contact Details data for customer {}", customer.getContactId());
        return customerContactDetailsService.getCustomerContactDetailsById(customer.getContactId());
    }

    /*@QueryMapping
    public Customer customerByName(@Argument String name) {
        log.info("Quering customer in GraphQL Server by name {}", name);
        Customer customer = customerService.getCustomerByName(name).get(0);
        return customer;
    }*/


    @MutationMapping
    public Customer addCustomer(@Argument String name, @Argument String role,
                           @Argument int age, @Argument String email,@Argument String mobile, @Argument String address) {

        CustomerContactDetails customerContactDetails = new CustomerContactDetails();
        customerContactDetails.setId(UUID.randomUUID().toString());
        customerContactDetails.setEmail(email);
        customerContactDetails.setMobile(mobile);
        customerContactDetails.setAddress(address);
        customerContactDetailsService.addCustomerContactDetails(customerContactDetails);

        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setName(name);
        customer.setRole(role);
        customer.setAge(age);
        customer.setContactId(customerContactDetails.getId());

        customerService.addCustomer(customer);

        return customer;
    }

    /*@PostMapping("/customers/save")
    public Customer addCustomer(@RequestBody Customer customer) {
        log.info("Adding user {} to the Database", customer.getName());
        return customerService.addCustomer(customer);
    }*/
}
