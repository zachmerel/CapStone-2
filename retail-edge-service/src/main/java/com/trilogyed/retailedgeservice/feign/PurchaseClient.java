package com.trilogyed.retailedgeservice.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "invoice-crud-service")
public interface PurchaseClient {
}
