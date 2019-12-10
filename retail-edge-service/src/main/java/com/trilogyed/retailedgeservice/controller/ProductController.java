package com.trilogyed.retailedgeservice.controller;

import com.trilogyed.retailedgeservice.dto.Inventory;
import com.trilogyed.retailedgeservice.dto.Product;
import com.trilogyed.retailedgeservice.service.RetailServiceLayer;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@CacheConfig(cacheNames = {"products"})
public class ProductController {
    private RetailServiceLayer retailServiceLayer;

    @RequestMapping(value = "/products/inventory", method = RequestMethod.GET)
    public List<Product> getProductsInInventory() {
        return retailServiceLayer.getProductsInInventory();
    }

    @Cacheable
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id) {
        return retailServiceLayer.getProductById(id);
    }

    @Cacheable
    @RequestMapping(value = "/products/invoice/{id}", method = RequestMethod.GET)
    public List<Product> getProductByInvoiceId(@PathVariable int id) {
        return retailServiceLayer.getProductByInvoiceId(id);
    }

    @CachePut(key = "#result.getProductId()")
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product o) {
        return retailServiceLayer.createProduct(o);
    }

    @CacheEvict(key = "inventory.getProductId()")
    @RequestMapping(value = "/product/inventory", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProductInventory(@RequestBody Inventory inventory) {
        retailServiceLayer.updateProductInventory(inventory);
    }

    @CacheEvict(key = "#product.getProductId()")
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product product) {
        retailServiceLayer.updateProduct(product);
    }

    @CacheEvict
    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) {
        retailServiceLayer.deleteProductById(id);
    }

}
