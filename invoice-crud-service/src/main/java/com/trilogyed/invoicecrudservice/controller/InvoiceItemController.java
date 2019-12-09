package com.trilogyed.invoicecrudservice.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.trilogyed.invoicecrudservice.dao.InvoiceItemRepo;
import com.trilogyed.invoicecrudservice.dto.InvoiceItem;
import com.trilogyed.invoicecrudservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/invoiceItem")
public class InvoiceItemController {
    @Autowired
    private InvoiceItemRepo invoiceItemRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        return invoiceItemRepo.save(invoiceItem);
    }

    @GetMapping
    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemRepo.findAll();
    }

    @GetMapping(value = "/invoice/{id}")
    public List<InvoiceItem> findInvoiceItemsByInvoiceId(@PathVariable int id) {
        return invoiceItemRepo.findInvoiceItemsByInvoiceId(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        invoiceItemRepo.save(invoiceItem);
    }

    @GetMapping(value = "/{id}")
    public InvoiceItem getInvoiceItemById(@PathVariable int id) throws JsonMappingException {
        Optional<InvoiceItem> invoiceItem = invoiceItemRepo.findById(id);
        if (invoiceItem.isPresent()) {
            return invoiceItem.get();
        } else {
            throw new NotFoundException("Did not find an invoiceItem with id " + id);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceItemById(@PathVariable int id) throws JsonMappingException {
        Optional<InvoiceItem> invoiceItem = invoiceItemRepo.findById(id);
        if (invoiceItem.isPresent()) {
            invoiceItemRepo.deleteById(id);
        } else {
            throw new NotFoundException("Did not find an invoiceItem with id " + id);
        }
    }
}