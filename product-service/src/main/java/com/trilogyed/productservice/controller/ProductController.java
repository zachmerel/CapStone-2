package com.trilogyed.productservice.controller;

//import com.trilogyed.productservice.controller.ProductDao;
//import com.trilogyed.productservice.controller.Product;

import com.trilogyed.productservice.dao.ProductDao;
import com.trilogyed.productservice.model.Inventory;
import com.trilogyed.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody @Valid Product o) {
        return productDao.save(o);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable int id) throws IllegalArgumentException {
        try {
            return productDao.getOne(id);
        } catch (NullPointerException n) {
            throw new IllegalArgumentException("illegal argument or another exception idk");
        }
    }

    @RequestMapping(value = "/product/inventory", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateProductInventory(@RequestBody Inventory inventory) throws Exception {
        Product productToGet = productDao.getOne(inventory.getProductId());
        productToGet.setinventory(inventory.getInventory());
        productDao.save(productToGet);
        return "Update: Successful";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(@RequestBody Product o) throws Exception {
            productDao.save(o);
            return "Update: Successful";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteProduct(@PathVariable int id) {
        try {
            productDao.deleteById(id);
            return "Delete: Success";
        } catch (Exception e) {
            return "Delete: Fail";
        }
    }
}
