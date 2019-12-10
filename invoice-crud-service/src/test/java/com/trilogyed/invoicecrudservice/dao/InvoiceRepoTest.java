package com.trilogyed.invoicecrudservice.dao;

import com.trilogyed.invoicecrudservice.dto.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceRepoTest {
    @Autowired
    InvoiceRepo invoiceRepo;
    @Before
    public void setUp() throws Exception {
        invoiceRepo.findAll().forEach(x->invoiceRepo.delete(x));
    }

    @Test
    public void findInvoicesByCustomerId() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.now());
        Invoice invoiceWithId = invoiceRepo.save(invoice);
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoiceWithId);
        assertEquals(invoiceList,invoiceRepo.findInvoicesByCustomerId(1));
    }
}