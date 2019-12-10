package com.trilogyed.retailedgeservice.dto;

import java.util.Objects;

public class Inventory {
    private Integer productId;
    private int inventory;

    public Inventory() {
    }

    public Inventory(int inventory) {
        this.inventory = inventory;
    }

    public Inventory(Integer productId, int inventory) {
        this.productId = productId;
        this.inventory = inventory;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "productId=" + productId +
                ", inventory=" + inventory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory1 = (Inventory) o;
        return inventory == inventory1.inventory &&
                Objects.equals(productId, inventory1.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, inventory);
    }
}
