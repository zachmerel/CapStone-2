package com.trilogyed.retailedgeservice.controller;

import com.trilogyed.retailedgeservice.dto.*;
import com.trilogyed.retailedgeservice.service.RetailServiceLayer;
import com.trilogyed.retailedgeservice.view.InvoiceViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class RetailController {
    public static final String EXCHANGE = "queue-demo-exchange";
    public static final String ROUTING_KEY = "email.list.add.${account}.controller";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RetailController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    private RetailServiceLayer retailServiceLayer;

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public Invoice submitInvoice(@RequestBody Invoice invoice) {
        return retailServiceLayer.createInvoice(invoice);
    }

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

    @RequestMapping(value = "/products/inventory", method = RequestMethod.GET)
    public List<Product> getProductsInInventory() {
        return retailServiceLayer.getProductsInInventory();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id) {
        return retailServiceLayer.getProductById(id);
    }

    @RequestMapping(value = "/products/invoice/{id}", method = RequestMethod.GET)
    public List<Product> getProductByInvoiceId(@PathVariable int id) {
        return retailServiceLayer.getProductByInvoiceId(id);
    }

    @RequestMapping(value = "/levelup/customer/{id}", method = RequestMethod.GET)
    public Optional<Integer> getLevelUpPointsByCustomerId(int id) {
        return retailServiceLayer.getLevelUpPointsByCustomerId(id);
    }

    @PostMapping(value = "/order/email/{email}")
    public InvoiceViewModel order(@PathVariable String email, @RequestBody Map<Integer, Integer> itemQuantityMap) {
        return retailServiceLayer.order(email, itemQuantityMap);
    }
    //-------------------------imported from zack
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody Customer o) {
        return retailServiceLayer.createCustomer(o);
    }
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id) throws IllegalArgumentException {
        return retailServiceLayer.getCustomerById(id);
    }
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return retailServiceLayer.getAllCustomers();
    }
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@RequestBody Customer o) throws Exception {
        retailServiceLayer.updateCustomer(o);
    }
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        retailServiceLayer.deleteCustomerById(id);
    }
    //PRODUCT URIs
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product o) {
        return retailServiceLayer.createProduct(o);
    }
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable int id) throws IllegalArgumentException {
        return retailServiceLayer.getProductById(id);
    }
    @RequestMapping(value = "/product/inventory", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProductInventory(@RequestBody Inventory inventory) throws Exception {
        retailServiceLayer.updateProductInventory(inventory);
    }
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return retailServiceLayer.getAllProducts();
    }
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product o) throws Exception {
        retailServiceLayer.updateProduct(o);
    }
    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) {
        retailServiceLayer.deleteProductById(id);
    }
    //    //INVOICE URIs
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceById(@PathVariable int id) {
        retailServiceLayer.deleteInvoiceById(id);
    }
    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody @Valid Invoice invoice) {
        retailServiceLayer.updateInvoice(invoice);
    }
    //LEVEL UP URIs
    @RequestMapping(value = "/levelUp/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public LevelUp getLevelUpById(@PathVariable int id) {
        return retailServiceLayer.getLevelUpById(id);
    }
    @RequestMapping(value = "/levelUp/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUpById(@PathVariable int id) {
        retailServiceLayer.deleteLevelUpById(id);
    }
    @RequestMapping(value = "/levelUp", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createLevelUp(@RequestBody @Valid LevelUp levelUp) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, levelUp);
        System.out.println("Message Sent");
        return "levelUp created: "+levelUp;
    }
    @RequestMapping(value = "/levelUp", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUp> getAllLevelUps() {
        return retailServiceLayer.getAllLevelUps();
    }
    @RequestMapping(value = "/levelUp", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, levelUp);
        System.out.println("Message Sent");
    }
}
