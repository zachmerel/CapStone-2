package com.trilogyed.retailedgeservice.controller;

import com.trilogyed.retailedgeservice.dto.Invoice;
import com.trilogyed.retailedgeservice.exceptions.NotFoundException;
import com.trilogyed.retailedgeservice.service.RetailServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CacheConfig(cacheNames = {"invoices"})
@RefreshScope
public class InvoiceController {
    @Autowired
    private RetailServiceLayer retailServiceLayer;

    @CachePut(key = "#result.getInvoiceId()")
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice) {
        return retailServiceLayer.createInvoice(invoice);
    }

    @Cacheable
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable int id) {
        try{
        return retailServiceLayer.getInvoiceById(id);}catch (Exception e){
            throw new NotFoundException("could not find an invoice with an id of "+id);
        }
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices() {
        return retailServiceLayer.getAllInvoices();
    }

    @RequestMapping(value = "/invoice/customer/{id}", method = RequestMethod.GET)
    public List<Invoice> getInvoicesByCustomerId(@PathVariable int id) {
        return retailServiceLayer.getInvoicesByCustomerId(id);
    }

    @CacheEvict
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceById(@PathVariable int id) {
        try{
        retailServiceLayer.deleteInvoiceById(id);}catch (Exception e){
            throw new NotFoundException("could not find an invoice with an id of "+id);
        }
    }

    @CacheEvict
    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateLevelUp(@RequestBody @Valid Invoice invoice) {
        retailServiceLayer.updateInvoice(invoice);
    }

}
