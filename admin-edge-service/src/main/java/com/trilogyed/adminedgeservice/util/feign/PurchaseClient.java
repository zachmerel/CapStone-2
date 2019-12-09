package com.trilogyed.adminedgeservice.util.feign;

import com.trilogyed.adminedgeservice.dto.Invoice;
import com.trilogyed.adminedgeservice.dto.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-crud-service")
public interface PurchaseClient {

    @PostMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice);

    @GetMapping(value = "/invoice")
    public List<Invoice> getAllInvoices();

    @PutMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInvoice(@RequestBody @Valid Invoice invoice);

    @GetMapping(value = "/invoice/{id}")
    public Invoice getInvoiceById(@PathVariable int id);

    @DeleteMapping(value = "/invoice/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceById(@PathVariable int id);

    @GetMapping(value = "/invoice/customer/{id}")
    public List<Invoice> findInvoicesByCustomerId(@PathVariable int id);

    @PostMapping(value = "/invoiceItem")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @GetMapping(value = "/invoiceItem")
    public List<InvoiceItem> getAllInvoiceItems();

    @PutMapping(value = "/invoiceItem")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @GetMapping(value = "/invoiceItem/{id}")
    public InvoiceItem getInvoiceItemById(@PathVariable int id);

    @DeleteMapping(value = "/invoiceItem/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceItemById(@PathVariable int id);

    @GetMapping(value = "/invoiceItem/invoice/{id}")
    public List<InvoiceItem> findInvoiceItemsByInvoiceId(@PathVariable int id);
}

