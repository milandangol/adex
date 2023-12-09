package com.levi.microservicedemo.service;

import com.levi.microservicedemo.domain.Customer;
import com.levi.microservicedemo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public Customer getCustomerById(String id) {
        log.info("Getting customer {} from the repository.", id);

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer == null || optionalCustomer.get() == null) {
            log.info("No customer found by id {}", id);
            return null;
        }

        Customer customer = optionalCustomer.get();
        log.info("found {} customers by id {}", customer.getName(), id);
        return customer;
    }

    public List<Customer> getCustomerByName(String name) {
        log.info("Getting customer {} from the repository.", name);

        List<Customer> customerList = customerRepository.findByName(name);

        if (CollectionUtils.isEmpty(customerList)) {
            log.info("No customer found by name {}", name);
            return new ArrayList<Customer>();
        }
        log.info("found {} customers by name {}", customerList.size(), name);
        return customerList;
    }






    public Customer addCustomer(Customer customer) {
        log.info("Adding customer {} to database", customer.getName());
        Customer customer2 = customerRepository.insert(customer);
        log.info("Added customer {} successfully", customer2.getName());
        return customer2;
    }
}
