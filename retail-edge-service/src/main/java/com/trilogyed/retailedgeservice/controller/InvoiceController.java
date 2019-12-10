package com.trilogyed.retailedgeservice.controller;

import com.trilogyed.retailedgeservice.dto.Invoice;
import com.trilogyed.retailedgeservice.service.RetailServiceLayer;
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
    private RetailServiceLayer retailServiceLayer;

    @CachePut(key = "#result.getInvoiceId()")
    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public Invoice submitInvoice(@RequestBody Invoice invoice) {
        return retailServiceLayer.createInvoice(invoice);
    }

    @Cacheable
    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable int id) {
        return retailServiceLayer.getInvoiceById(id);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices() {
        return retailServiceLayer.getAllInvoices();
    }

    @RequestMapping(value = "/invoices/customer/{id}", method = RequestMethod.GET)
    public List<Invoice> getInvoicesByCustomerId(@PathVariable int id) {
        return retailServiceLayer.getInvoicesByCustomerId(id);
    }

    @CacheEvict
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceById(@PathVariable int id) {
        retailServiceLayer.deleteInvoiceById(id);
    }

    @CacheEvict
    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody @Valid Invoice invoice) {
        retailServiceLayer.updateInvoice(invoice);
    }

}
