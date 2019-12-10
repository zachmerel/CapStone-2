package com.trilogyed.retailedgeservice.controller;

import com.trilogyed.retailedgeservice.service.RetailServiceLayer;
import com.trilogyed.retailedgeservice.view.InvoiceViewModel;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class RetailController {
    private RetailServiceLayer retailServiceLayer;
    @PostMapping(value = "/order/email/{email}")
    public InvoiceViewModel order(@PathVariable String email, @RequestBody Map<Integer, Integer> itemQuantityMap) {
        return retailServiceLayer.order(email, itemQuantityMap);
    }
}
