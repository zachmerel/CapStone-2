package com.trilogyed.customerservice.dao;


import com.trilogyed.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    List<Customer> findCustomersByEmail(String email);
}
