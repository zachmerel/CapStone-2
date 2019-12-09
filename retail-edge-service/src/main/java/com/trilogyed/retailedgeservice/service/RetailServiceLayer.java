package com.trilogyed.retailedgeservice.service;

import com.trilogyed.retailedgeservice.dto.Invoice;
import com.trilogyed.retailedgeservice.dto.LevelUp;
import com.trilogyed.retailedgeservice.dto.Product;
import com.trilogyed.retailedgeservice.exceptions.MultipleCustomersException;
import com.trilogyed.retailedgeservice.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return invoiceClient.createInvoice(invoice);
    }

    public Invoice getInvoiceById(int id) {
        return invoiceClient.getInvoiceById(id);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceClient.getAllInvoices();
    }

    public List<Invoice> getInvoicesByCustomerId(int customerId) {
        return invoiceClient.findInvoicesByCustomerId(customerId);
    }

    public List<Product> getProductsInInventory() {
        return productClient.getAllProducts();
    }

    public Product getProductById(int id) {
        return productClient.getProductById(id);
    }

    public List<Product> getProductByInvoiceId(int id) {
        List<Product> products = new ArrayList<>();
        invoiceItemClient.findInvoiceItemsByInvoiceId(id).forEach(x->
                products.add(
                        productClient.getProductById(
                                x.getProductId()
                        )));
        return products;
    }

    public Optional<Integer> getLevelUpPointsByCustomerId(int id) {
        List<LevelUp> levelUps= levelUpClient.findLevelUpByCustomerId(id);
        if (levelUps.size()==0){
            return Optional.empty();
        }else if(
                levelUps.size()>1
        ){
            throw new MultipleCustomersException("There were multiple LevelUp accounts associated with customerId "+id);
        }else
        return Optional.of(levelUps.get(0).getPoints());
    }
}
