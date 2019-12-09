package com.trilogyed.retailedgeservice.controller;

import com.trilogyed.retailedgeservice.dto.Invoice;
import com.trilogyed.retailedgeservice.dto.InvoiceItem;
import com.trilogyed.retailedgeservice.dto.Product;
import com.trilogyed.retailedgeservice.service.RetailServiceLayer;
import com.trilogyed.retailedgeservice.view.InvoiceViewModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class RetailController {
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
    public InvoiceViewModel order(@PathVariable String email, @RequestBody Map<Integer,Integer> itemQuantityMap){
        return retailServiceLayer.order(email,itemQuantityMap);
    }
}
