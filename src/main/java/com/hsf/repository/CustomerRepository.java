package com.hsf.repository;

import com.hsf.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Customer findCustomerByEmail(String email);
}
