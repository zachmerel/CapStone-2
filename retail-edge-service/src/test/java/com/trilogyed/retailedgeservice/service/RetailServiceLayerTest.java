package com.trilogyed.retailedgeservice.service;

import com.trilogyed.retailedgeservice.dto.*;
import com.trilogyed.retailedgeservice.feign.*;
import org.junit.Before;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

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
        productClient = mock(ProductClient.class);
        Product product = new Product();
        //        product.setCustomerId(1);
        //        product.setPoints(1);
        //        product.setMemberDate(LocalDate.parse("2019-12-05"));
        Product productWithId = new Product();
        //        productWithId.setCustomerId(1);
        //        productWithId.setPoints(1);
        //        productWithId.setMemberDate(LocalDate.parse("2019-12-05"));
        //        productWithId.setProductId(1);
        List<Product> productList = new ArrayList<>();
        productList.add(productWithId);
        doReturn(productWithId).when(productClient).getProductById(1);
        doReturn(productList).when(productClient).getAllProducts();
    }

    private void setUpLevelUpClientMock() {
        levelUpClient = mock(LevelUpClient.class);
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(1);
        levelUp.setMemberDate(LocalDate.parse("2019-12-05"));
        LevelUp levelUpWithId = new LevelUp();
        levelUpWithId.setCustomerId(1);
        levelUpWithId.setPoints(1);
        levelUpWithId.setMemberDate(LocalDate.parse("2019-12-05"));
        levelUpWithId.setLevelUpId(1);
        List<LevelUp> levelUpList = new ArrayList<>();
        levelUpList.add(levelUpWithId);
        doReturn(levelUpWithId).when(levelUpClient).getLevelUpById(1);
        doReturn(levelUpList).when(levelUpClient).getAllLevelUps();
    }

    private void setUpInvoiceItemClientMock() {
        invoiceItemClient = mock(InvoiceItemClient.class);
        InvoiceItem invoiceItem = new InvoiceItem();
        //        invoiceItem.setCustomerId(1);
        //        invoiceItem.setPoints(1);
        //        invoiceItem.setMemberDate(LocalDate.parse("2019-12-05"));
        InvoiceItem invoiceItemWithId = new InvoiceItem();
        //        invoiceItemWithId.setCustomerId(1);
        //        invoiceItemWithId.setPoints(1);
        //        invoiceItemWithId.setMemberDate(LocalDate.parse("2019-12-05"));
        //        invoiceItemWithId.setInvoiceItemId(1);
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItemWithId);
        doReturn(invoiceItemWithId).when(invoiceItemClient).getInvoiceItemById(1);
        doReturn(invoiceItemList).when(invoiceItemClient).getAllInvoiceItems();
    }

    private void setUpInvoiceClientMock() {
        invoiceClient = mock(InvoiceClient.class);
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.parse("2019-12-05"));
        Invoice invoiceWithId = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.parse("2019-12-05"));
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoiceWithId);
        doReturn(invoiceWithId).when(invoiceClient).getInvoiceById(1);
        doReturn(invoiceList).when(invoiceClient).getAllInvoices();
    }

    private void setUpCustomerClientMock() {
        customerClient = mock(CustomerClient.class);
        Customer customer = new Customer();
        //        customer.setCustomerId(1);
        //        customer.setPoints(1);
        //        customer.setMemberDate(LocalDate.parse("2019-12-05"));
        Customer customerWithId = new Customer();
        //        customerWithId.setCustomerId(1);
        //        customerWithId.setPoints(1);
        //        customerWithId.setMemberDate(LocalDate.parse("2019-12-05"));
        //        customerWithId.setCustomerId(1);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customerWithId);
        doReturn(customerWithId).when(customerClient).getCustomerById(1);
        doReturn(customerList).when(customerClient).getAllCustomers();
    }
}