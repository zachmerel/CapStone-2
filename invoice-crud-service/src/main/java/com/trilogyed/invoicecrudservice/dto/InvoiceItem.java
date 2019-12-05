package com.trilogyed.invoicecrudservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice_item")
//@Proxy(lazy = false)
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer invoiceItemId;
    @Min(value = 1, message = "invoiceId should be specified as a positive int.")
    private int invoiceId;
    @Min(value = 1, message = "productId should be specified as a positive int.")
    private int productId;
    @Min(value = 1, message = "quantity should be specified as a positive int.")
    private int quantity;
    @Min(value = 1, message = "unitPrice should be specified as a positive int.")
    private double unitPrice;

    public InvoiceItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return invoiceId == that.invoiceId &&
                productId == that.productId &&
                quantity == that.quantity &&
                Double.compare(that.unitPrice, unitPrice) == 0 &&
                Objects.equals(invoiceItemId, that.invoiceItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, productId, quantity, unitPrice);
    }

    public Integer getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(Integer invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
