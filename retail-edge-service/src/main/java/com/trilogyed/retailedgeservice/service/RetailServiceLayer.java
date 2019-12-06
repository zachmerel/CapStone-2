package com.trilogyed.retailedgeservice.service;

import com.trilogyed.retailedgeservice.dto.Invoice;
import com.trilogyed.retailedgeservice.dto.Product;
import com.trilogyed.retailedgeservice.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetailServiceLayer {
    private CustomerClient customerClient;
    private PurchaseClient invoiceClient;
    private PurchaseClient invoiceItemClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;

    @Autowired
    public RetailServiceLayer(CustomerClient customerClient, PurchaseClient invoiceClient, PurchaseClient invoiceItemClient, LevelUpClient levelUpClient, ProductClient productClient) {
        this.customerClient = customerClient;
        this.invoiceClient = invoiceClient;
        this.invoiceItemClient = invoiceItemClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }

    public Invoice submitInvoice(Invoice invoice) {
        return ((InvoiceClient)invoiceClient).createInvoice(invoice);
    }

    public Invoice getInvoiceById(int id) {
        return null;
    }

    public List<Invoice> getAllInvoices() {
        return null;
    }

    public List<Invoice> getInvoicesByCustomerId() {
        return null;
    }

    public List<Product> getProductsInInventory() {
        return null;
    }

    public Product getProductById(int id) {
        return null;
    }

    public List<Product> getProductByInvoiceId(int id) {
        return null;
    }

    public int getLevelUpPointsByCustomerId(int id) {
        return 0;
    }
}
