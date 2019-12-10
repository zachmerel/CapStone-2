package com.trilogyed.retailedgeservice.feign;

import com.trilogyed.retailedgeservice.dto.Inventory;
import com.trilogyed.retailedgeservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "product-service")
@RequestMapping(value = "/product")
public interface ProductClient {

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product o);

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable int id);

    @RequestMapping(value = "/product/inventory", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateProductInventory(@RequestBody @Valid Inventory inventory);

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts();

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(@RequestBody Product o);

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteProductById(@PathVariable int id);


}
