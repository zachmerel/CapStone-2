package com.trilogyed.adminedgeservice.service;

import com.trilogyed.adminedgeservice.dto.*;
import com.trilogyed.adminedgeservice.util.feign.CustomerClient;
import com.trilogyed.adminedgeservice.util.feign.LevelUpClient;
import com.trilogyed.adminedgeservice.util.feign.ProductClient;
import com.trilogyed.adminedgeservice.util.feign.PurchaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceLayer {
    private CustomerClient customerClient;
    private PurchaseClient invoiceClient;
    private PurchaseClient invoiceItemClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;

    @Autowired
    public AdminServiceLayer(CustomerClient customerClient, PurchaseClient invoiceClient, PurchaseClient invoiceItemClient, LevelUpClient levelUpClient, ProductClient productClient) {
        this.customerClient = customerClient;
        this.invoiceClient = invoiceClient;
        this.invoiceItemClient = invoiceItemClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }

    //CUSTOMER
    public Customer getCustomerById(int id){
        return customerClient.getCustomer(id);
    }

    public Customer createCustomer(Customer customer){
        return customerClient.saveCustomer(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerClient.getAllCustomers();
    }

    public String updateCustomer(Customer customer){
       return customerClient.updateCustomer(customer);
    }

    public String deleteCustomerById(int id){
       return customerClient.deleteCustomer(id);
    }

    //PRODUCTS
    public Product createProduct(Product product){
        return productClient.saveProduct(product);
    }

    public Product getProductById(int id){
        return productClient.getProduct(id);
    }

    public void updateProductInventory(Inventory inventory){
        productClient.updateProductInventory(inventory);
    }

    public List<Product> getAllProducts(){
        return productClient.getAllProducts();
    }

    public String updateProduct(Product product){
        return productClient.updateProduct(product);
    }

    public String deleteProductById(int id){
        return productClient.deleteProduct(id);
    }

    //INVOICE
    public Invoice createInvoice(Invoice invoice){
        return invoiceClient.createInvoice(invoice);
    }

    public Invoice getInvoiceById(int id){
        return invoiceClient.getInvoiceById(id);
    }

    public List<Invoice> getAllInvoices(){
        return invoiceClient.getAllInvoices();
    }

    public List<Invoice> getInvoicesByCustomerId(int id){
        return invoiceClient.findInvoicesByCustomerId(id);
    }

    public void deleteInvoiceById(int id){
        invoiceClient.deleteInvoiceById(id);
    }

    public void updateInvoice(Invoice invoice){
        invoiceClient.updateInvoice(invoice);
    }

    //LEVEL UPS

    public LevelUp createLevelUp(LevelUp levelUp){
        return levelUpClient.createLevelUp(levelUp);
    }

    public LevelUp getLevelUpById(int id){
        return levelUpClient.getLevelUpById(id);
    }

    public List<LevelUp> getAllLevelUps(){
        return levelUpClient.getAllLevelUps();
    }

    public void deleteLevelUpById(int id){
        levelUpClient.deleteLevelUpById(id);
    }

    public void updateLevelUp(LevelUp levelUp){
        levelUpClient.updateLevelUp(levelUp);
    }

}
