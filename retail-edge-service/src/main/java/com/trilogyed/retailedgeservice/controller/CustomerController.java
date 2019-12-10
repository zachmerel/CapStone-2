package com.trilogyed.retailedgeservice.controller;

import com.trilogyed.retailedgeservice.dto.Customer;
import com.trilogyed.retailedgeservice.service.RetailServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CacheConfig(cacheNames = {"customers"})
@RefreshScope
public class CustomerController {
    @Autowired
    private RetailServiceLayer retailServiceLayer;

    @CachePut(key = "#result.getCustomerId()")
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody Customer o) {
        return retailServiceLayer.createCustomer(o);
    }

    @Cacheable
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id) throws IllegalArgumentException {
        return retailServiceLayer.getCustomerById(id);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return retailServiceLayer.getAllCustomers();
    }

    @CacheEvict
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@RequestBody Customer o) {
        retailServiceLayer.updateCustomer(o);
    }

    @CacheEvict
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        retailServiceLayer.deleteCustomerById(id);
    }
}
