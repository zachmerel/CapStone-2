package com.trilogyed.retailedgeservice.dto;

import java.util.Objects;

public class Product {
    private Integer productId;
    private String product_name;
    private String product_description;
    private double list_price;
    private double unit_price;
    private int inventory;


    public Product() {
    }

    public Product(int inventory) {
        this.inventory = inventory;
    }

    public Product(String product_name, String product_description, double list_price, double unit_price, int inventory) {
        this.product_name = product_name;
        this.product_description = product_description;
        this.list_price = list_price;
        this.unit_price = unit_price;
        this.inventory = inventory;
    }

    public Product(Integer productId, String product_name, String product_description, double list_price, double unit_price, int inventory) {
        this.productId = productId;
        this.product_name = product_name;
        this.product_description = product_description;
        this.list_price = list_price;
        this.unit_price = unit_price;
        this.inventory = inventory;
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

    public double getunit_price() {
        return unit_price;
    }

    public void setunit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getinventory() {
        return inventory;
    }

    public void setinventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", product_name='" + product_name + '\'' +
                ", product_description='" + product_description + '\'' +
                ", list_price=" + list_price +
                ", unit_price=" + unit_price +
                ", inventory=" + inventory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.list_price, list_price) == 0 &&
                Double.compare(product.unit_price, unit_price) == 0 &&
                inventory == product.inventory &&
                Objects.equals(productId, product.productId) &&
                Objects.equals(product_name, product.product_name) &&
                Objects.equals(product_description, product.product_description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, product_name, product_description, list_price, unit_price, inventory);
    }
}
