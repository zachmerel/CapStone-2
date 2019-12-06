package com.trilogyed.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    private String product_name;
    private String product_description;
    private double list_price;
    private double unit_cost;


    public Product() {
    }

    public Product(String product_name, String product_description, double list_price, double unit_cost) {
        this.product_name = product_name;
        this.product_description = product_description;
        this.list_price = list_price;
        this.unit_cost = unit_cost;
    }

    public Product(Integer productId, String product_name, String product_description, double list_price, double unit_cost) {
        this.productId = productId;
        this.product_name = product_name;
        this.product_description = product_description;
        this.list_price = list_price;
        this.unit_cost = unit_cost;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getproduct_name() {
        return product_name;
    }

    public void setproduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getproduct_description() {
        return product_description;
    }

    public void setproduct_description(String product_description) {
        this.product_description = product_description;
    }

    public double getlist_price() {
        return list_price;
    }

    public void setlist_price(double list_price) {
        this.list_price = list_price;
    }

    public double getunit_cost() {
        return unit_cost;
    }

    public void setunit_cost(double unit_cost) {
        this.unit_cost = unit_cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", product_name='" + product_name + '\'' +
                ", product_description='" + product_description + '\'' +
                ", list_price=" + list_price +
                ", unit_cost=" + unit_cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.list_price, list_price) == 0 &&
                Double.compare(product.unit_cost, unit_cost) == 0 &&
                Objects.equals(productId, product.productId) &&
                Objects.equals(product_name, product.product_name) &&
                Objects.equals(product_description, product.product_description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, product_name, product_description, list_price, unit_cost);
    }
}
