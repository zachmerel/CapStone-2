package com.trilogyed.retailedgeservice.feign;

import com.trilogyed.retailedgeservice.dto.Inventory;
import com.trilogyed.retailedgeservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product product);

    @GetMapping
    public List<Product> getAllProducts();

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody @Valid Product product);

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable int id);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable int id);

    @RequestMapping(value = "/product/inventory", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateProductInventory(@RequestBody Inventory inventory);


}
