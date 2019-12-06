package com.trilogyed.retailedgeservice.feign;

import com.trilogyed.retailedgeservice.dto.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-crud-service")
public interface InvoiceClient {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice);

    @GetMapping
    public List<Invoice> getAllInvoices();

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInvoice(@RequestBody @Valid Invoice invoice);

    @GetMapping(value = "/{id}")
    public Invoice getInvoiceById(@PathVariable int id);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceById(@PathVariable int id);

    @GetMapping(value = "/{id}")
    public List<Invoice> findInvoicesByCustomerId(@PathVariable int id);
}
