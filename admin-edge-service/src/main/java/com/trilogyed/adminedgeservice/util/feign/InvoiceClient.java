package com.trilogyed.adminedgeservice.util.feign;

import com.trilogyed.adminedgeservice.dto.Invoice;
import com.trilogyed.adminedgeservice.dto.InvoiceItem;
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

    //INVOICE ITEM FEIGN
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
}
