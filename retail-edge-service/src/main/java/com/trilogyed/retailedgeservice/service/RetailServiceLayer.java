package com.trilogyed.retailedgeservice.service;

import com.netflix.discovery.converters.Auto;
import com.trilogyed.retailedgeservice.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetailServiceLayer {
    private CustomerClient customerClient;
    private InvoiceClient invoiceClient;
    private InvoiceItemClient invoiceItemClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;

    @Autowired
    public RetailServiceLayer(CustomerClient customerClient, InvoiceClient invoiceClient, InvoiceItemClient invoiceItemClient, LevelUpClient levelUpClient, ProductClient productClient) {
        this.customerClient = customerClient;
        this.invoiceClient = invoiceClient;
        this.invoiceItemClient = invoiceItemClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }
}
