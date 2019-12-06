package com.trilogyed.productservice.dao;

//import com.trilogyed.productservice.dao.model.Product;

import com.trilogyed.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
}
