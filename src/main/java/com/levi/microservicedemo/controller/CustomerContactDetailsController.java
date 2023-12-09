package com.levi.microservicedemo.controller;

import com.levi.microservicedemo.domain.Customer;
import com.levi.microservicedemo.domain.CustomerContactDetails;
import com.levi.microservicedemo.service.CustomerContactDetailsService;
import com.levi.microservicedemo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CustomerContactDetailsController {

    @Autowired
    CustomerContactDetailsService customerContactDetailsService;

    @GetMapping("/contacts/{email}")
    public CustomerContactDetails getCustomerByEmail(@PathVariable String email) {
        log.info("Getting customer contact details by email {} ", email);
        CustomerContactDetails customerContactDetails = customerContactDetailsService.getCustomerContactDetailsByEmail(email);
        log.info("Received {} customer contact detail by email {}", customerContactDetails.getMobile(), email);
        return customerContactDetails;
    }




    @PostMapping("/contacts/save")
    public CustomerContactDetails addCustomerContactDetails(@RequestBody CustomerContactDetails customerContactDetails) {
        log.info("Adding contact details {} to the Database", customerContactDetails.getEmail());
        return customerContactDetailsService.addCustomerContactDetails(customerContactDetails);
    }
}
