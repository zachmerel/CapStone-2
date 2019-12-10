package com.trilogyed.retailedgeservice.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.trilogyed.retailedgeservice.dto.InvoiceItem;
import com.trilogyed.retailedgeservice.service.RetailServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/invoiceItem")
@CacheConfig(cacheNames = {"invoiceItems"})
public class InvoiceItemController {
    @Autowired
    private RetailServiceLayer retailServiceLayer;

    @CachePut(key = "#result.getInvoiceItemId()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        return retailServiceLayer.createInvoiceItem(invoiceItem);
    }

    @GetMapping
    public List<InvoiceItem> getAllInvoiceItems() {
        return retailServiceLayer.getAllInvoiceItems();
    }

    @CacheEvict(key = "#invoiceItem.getInvoiceItemId()")
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        retailServiceLayer.updateInvoiceItem(invoiceItem);
    }

    @Cacheable
    @GetMapping(value = "/{id}")
    public InvoiceItem getInvoiceItemById(@PathVariable int id) {
        return retailServiceLayer.getInvoiceItemById(id);
    }

    @CacheEvict
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceItemById(@PathVariable int id) throws JsonMappingException {
        retailServiceLayer.deleteInvoiceItemById(id);
    }
}