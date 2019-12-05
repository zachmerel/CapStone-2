package com.trilogyed.invoicecrudservice.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.trilogyed.invoicecrudservice.dao.InvoiceRepo;
import com.trilogyed.invoicecrudservice.dto.Invoice;
import com.trilogyed.invoicecrudservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceRepo invoiceRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice) {
        return invoiceRepo.save(invoice);
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInvoice(@RequestBody @Valid Invoice invoice) {
        invoiceRepo.save(invoice);
    }

    @GetMapping(value = "/{id}")
    public Invoice getInvoiceById(@PathVariable int id) throws JsonMappingException {
        Optional<Invoice> invoice = invoiceRepo.findById(id);
        if (invoice.isPresent()) {
            return invoice.get();
        } else {
            throw new NotFoundException("Did not find an invoice with id " + id);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceById(@PathVariable int id) throws JsonMappingException {
        Optional<Invoice> invoice = invoiceRepo.findById(id);
        if (invoice.isPresent()) {
            invoiceRepo.deleteById(id);
        } else {
            throw new NotFoundException("Did not find an invoice with id " + id);
        }
    }
}