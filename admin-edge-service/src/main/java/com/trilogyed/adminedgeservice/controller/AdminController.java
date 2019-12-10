package com.trilogyed.adminedgeservice.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.trilogyed.adminedgeservice.dto.*;
import com.trilogyed.adminedgeservice.service.AdminServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {
    @Autowired
    private AdminServiceLayer adminServiceLayer;

    //CUSTOMER URI
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody @Valid Customer o) {
        return adminServiceLayer.createCustomer(o);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id) throws IllegalArgumentException {
        return adminServiceLayer.getCustomerById(id);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return adminServiceLayer.getAllCustomers();
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@RequestBody @Valid Customer o) throws Exception {
        adminServiceLayer.updateCustomer(o);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        adminServiceLayer.deleteCustomerById(id);
    }


    //PRODUCT URIs
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody @Valid Product o) {
        return adminServiceLayer.createProduct(o);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable int id) throws IllegalArgumentException {
        return adminServiceLayer.getProductById(id);
    }

    @RequestMapping(value = "/product/inventory", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProductInventory(@RequestBody Inventory inventory) throws Exception {
        adminServiceLayer.updateProductInventory(inventory);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return adminServiceLayer.getAllProducts();
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody @Valid Product o) throws Exception {
        adminServiceLayer.updateProduct(o);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) {
        adminServiceLayer.deleteProductById(id);
    }

    //INVOICE URIs
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice submitInvoice(@RequestBody @Valid Invoice invoice) {
        return adminServiceLayer.createInvoice(invoice);
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable int id) {
        return adminServiceLayer.getInvoiceById(id);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return adminServiceLayer.getAllInvoices();
    }

    @RequestMapping(value = "/invoice/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoicesByCustomerId(@PathVariable int id) {
        return adminServiceLayer.getInvoicesByCustomerId(id);
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceById(@PathVariable int id) {
        adminServiceLayer.deleteInvoiceById(id);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody @Valid Invoice invoice) {
        adminServiceLayer.updateInvoice(invoice);
    }

    //LEVEL UP URIs
    @RequestMapping(value = "/levelUp/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public LevelUp getLevelUpById(@PathVariable int id) {
        return adminServiceLayer.getLevelUpById(id);
    }

    @RequestMapping(value = "/levelUp/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUpById(@PathVariable int id) {
        adminServiceLayer.deleteLevelUpById(id);
    }

    @RequestMapping(value = "/levelUp", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody @Valid LevelUp levelUp) {
        return adminServiceLayer.createLevelUp(levelUp);
    }

    @RequestMapping(value = "/levelUp", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUp> getAllLevelUps() {
        return adminServiceLayer.getAllLevelUps();
    }

    @RequestMapping(value = "/levelUp", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp) {
        adminServiceLayer.updateLevelUp(levelUp);
    }

}
