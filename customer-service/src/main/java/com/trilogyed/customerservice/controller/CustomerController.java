package com.trilogyed.customerservice.controller;

//import com.trilogyed.customerservice.controller.CustomerDao;
//import com.trilogyed.customerservice.controller.Customer;

import com.trilogyed.customerservice.dao.CustomerDao;
import com.trilogyed.customerservice.exception.NotFoundException;
import com.trilogyed.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody @Valid Customer o) {
        return customerDao.save(o);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id) throws IllegalArgumentException {
        try {
            return customerDao.getOne(id);
        } catch (NullPointerException n) {
            throw new NotFoundException("no customer found with id:" + id);
        }
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateCustomer(@RequestBody @Valid  Customer o) throws Exception {
            customerDao.save(o);
            return "Update: Successful";
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
