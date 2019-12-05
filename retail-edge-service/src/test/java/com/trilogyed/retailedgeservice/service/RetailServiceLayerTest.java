package com.trilogyed.retailedgeservice.service;

import com.trilogyed.retailedgeservice.feign.*;
import org.junit.Before;

import static org.junit.Assert.*;

public class RetailServiceLayerTest {
    private CustomerClient customerClient;
    private InvoiceClient invoiceClient;
    private InvoiceItemClient invoiceItemClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;

    @Before
    public void setUp() {
        setUpCustomerClientMock();
        setUpInvoiceClientMock();
        setUpInvoiceItemClientMock();
        setUpLevelUpClientMock();
        setUpProductClientMock();

    }

    private void setUpProductClientMock() {
    }

    private void setUpLevelUpClientMock() {
    }

    private void setUpInvoiceItemClientMock() {
    }

    private void setUpInvoiceClientMock() {
    }

    private void setUpCustomerClientMock() {
    }
}