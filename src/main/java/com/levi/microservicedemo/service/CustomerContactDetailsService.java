package com.levi.microservicedemo.service;

import com.levi.microservicedemo.domain.Customer;
import com.levi.microservicedemo.domain.CustomerContactDetails;
import com.levi.microservicedemo.repository.CustomerContactDetailsRepository;
import com.levi.microservicedemo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerContactDetailsService {

    @Autowired
    CustomerContactDetailsRepository customerContactDetailsRepository;


    public CustomerContactDetails getCustomerContactDetailsById(String id) {
        log.info("Getting customer contact details {} from the repository.", id);

        Optional<CustomerContactDetails> optionalCustomer = customerContactDetailsRepository.findById(id);

        if (optionalCustomer == null || optionalCustomer.get() == null) {
            log.info("No customer found by id {}", id);
            return null;
        }

        CustomerContactDetails customerContactDetails = optionalCustomer.get();
        log.info("found {} customer contact details by id {}", customerContactDetails.getEmail(), id);
        return customerContactDetails;
    }

    public CustomerContactDetails getCustomerContactDetailsByEmail(String email) {
        log.info("Getting customer contact details {} from the repository.", email);

        CustomerContactDetails customerContactDetails = customerContactDetailsRepository.findByEmail(email);

        if (customerContactDetails == null) {
            log.info("No customer contact found by email {}", email);
            return null;
        }
        log.info("found {} customer contact details by email {}", customerContactDetails.getAddress(), email);
        return customerContactDetails;
    }


    public CustomerContactDetails getCustomerContactDetailsByMobile(String mobile) {
        log.info("Getting customer contact details {} from the repository.", mobile);

        CustomerContactDetails customerContactDetails = customerContactDetailsRepository.findByMobile(mobile);

        if (customerContactDetails == null) {
            log.info("No customer contact found by mobile {}", mobile);
            return null;
        }
        log.info("found {} customer contact details by mobile {}", customerContactDetails.getAddress(), mobile);
        return customerContactDetails;
    }



    public CustomerContactDetails addCustomerContactDetails(CustomerContactDetails customerContactDetails) {
        log.info("Adding customer contact details {} to database", customerContactDetails.getEmail());
        CustomerContactDetails customerContactDetails2 = customerContactDetailsRepository.insert(customerContactDetails);
        log.info("Added customer contact details {} successfully", customerContactDetails2.getEmail());
        return customerContactDetails2;
    }
}
