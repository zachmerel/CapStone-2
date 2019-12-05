package com.trilogyed.customerservice.controller;

//import com.trilogyed.customerservice.controller.CustomerDao;
//import com.trilogyed.customerservice.controller.Customer;

import com.trilogyed.customerservice.dao.CustomerDao;
import com.trilogyed.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody Customer o) {
        return customerDao.save(o);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id) throws IllegalArgumentException {
        try {
            return customerDao.getOne(id);
        } catch (NullPointerException n) {
            throw new IllegalArgumentException("illegal argument or another exception idk");
        }
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateCustomer(@RequestBody Customer o) throws Exception {
        try {
            customerDao.save(o);
            return "Update: Successful";
        } catch (Exception e) {
            return "Update: Fail";
        }
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteCustomer(@PathVariable int id) {
        try {
            customerDao.deleteById(id);
            return "Delete: Success";
        } catch (Exception e) {
            return "Delete: Fail";
        }
    }
}
