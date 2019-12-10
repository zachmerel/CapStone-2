package com.trilogyed.invoicecrudservice.dao;

import com.trilogyed.invoicecrudservice.dto.InvoiceItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemRepoTest {
    @Autowired
    InvoiceItemRepo invoiceItemRepo;
    @Before
    public void setUp() throws Exception {
        invoiceItemRepo.findAll().forEach(x->invoiceItemRepo.delete(x));
    }
    @Test
    public void findInvoiceItemsByInvoiceId() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setProductId(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnitPrice(1);
        InvoiceItem invoiceItemWithId = invoiceItemRepo.save(invoiceItem);
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItemWithId);
        assertEquals(invoiceItems,invoiceItemRepo.findInvoiceItemsByInvoiceId(1));
    }
}