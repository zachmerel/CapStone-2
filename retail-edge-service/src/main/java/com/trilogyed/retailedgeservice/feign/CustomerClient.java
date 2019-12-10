package com.trilogyed.retailedgeservice.feign;

import com.trilogyed.retailedgeservice.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@FeignClient(name = "customer-service")
@RequestMapping(value = "/customer")
public interface CustomerClient {

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody @Valid Customer o);

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id);

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers();

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateCustomer(@RequestBody @Valid  Customer o);

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteCustomer(@PathVariable int id);

    @GetMapping("/email/{email}")
    List<Customer> findCustomersByEmail(@PathVariable String email);
}
