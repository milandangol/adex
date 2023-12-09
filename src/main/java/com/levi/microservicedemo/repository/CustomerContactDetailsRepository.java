package com.levi.microservicedemo.repository;

import com.levi.microservicedemo.domain.Customer;
import com.levi.microservicedemo.domain.CustomerContactDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerContactDetailsRepository extends MongoRepository<CustomerContactDetails, String> {

    CustomerContactDetails findByEmail(String email);
    CustomerContactDetails findByMobile(String mobile);

}
