package com.trilogyed.retailedgeservice.view;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.trilogyed.retailedgeservice.dto.InvoiceItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {

    private Integer id;
    private CustomerViewModel customer;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate purchaseDate;
    private List<InvoiceItem> invoiceItems;
    public InvoiceViewModel() {
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "id=" + id +
                ", customer=" + customer +
                ", purchaseDate=" + purchaseDate +
                ", invoiceItems=" + invoiceItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(invoiceItems, that.invoiceItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, purchaseDate, invoiceItems);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerViewModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerViewModel customer) {
        this.customer = customer;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
}

