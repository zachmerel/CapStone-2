package com.trilogyed.retailedgeservice.feign;

import com.trilogyed.retailedgeservice.dto.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@FeignClient(name = "invoice-crud-service")
public interface InvoiceItemClient extends PurchaseClient{

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @GetMapping
    public List<InvoiceItem> getAllInvoiceItems();

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @GetMapping(value = "/{id}")
    public InvoiceItem getInvoiceItemById(@PathVariable int id);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceItemById(@PathVariable int id);

    @GetMapping(value = "/invoice/{id}")
    public List<InvoiceItem> findInvoiceItemsByInvoiceId(@PathVariable int id);
}
