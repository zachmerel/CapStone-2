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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Valid Customer customer);

    @GetMapping
    public List<Customer> getAllCustomers();

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@RequestBody @Valid Customer customer);

    @GetMapping(value = "/{id}")
    public Customer getCustomerById(@PathVariable int id);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable int id);
    @GetMapping("/email/{email}")
    List<Customer> findCustomersByEmail(@PathVariable String email);
}
