package com.trilogyed.adminedgeservice.service;

import com.trilogyed.adminedgeservice.dto.*;
import com.trilogyed.adminedgeservice.util.feign.CustomerClient;
import com.trilogyed.adminedgeservice.util.feign.LevelUpClient;
import com.trilogyed.adminedgeservice.util.feign.ProductClient;
import com.trilogyed.adminedgeservice.util.feign.PurchaseClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class AdminServiceLayerTest {
    private CustomerClient customerClient;
    private PurchaseClient invoiceClient;
    private PurchaseClient invoiceItemClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;
    private AdminServiceLayer service;

//    @MockBean



    @Before
    public void setUp() {
        setUpCustomerClientMock();
        setUpInvoiceClientMock();
        setUpLevelUpClientMock();
        setUpProductClientMock();
        service = new AdminServiceLayer(customerClient, invoiceClient, invoiceItemClient, levelUpClient, productClient);
    }

    @Test
    public void shouldGetCustomerById(){
        //Arrange
        Customer customer = new Customer();
        customer.setfirstName("Dan");
        customer.setlastName("Mueller");
        customer.setcity("Chicago");
        customer.setstreet("Fake Street");
        customer.setzip("60606");
        customer.setemail("danmuller@gmail.com");
        customer.setphone("7732025000");
        Customer customerWithId = new Customer();
        customerWithId.setCustomerId(1);
        customerWithId.setfirstName("Dan");
        customerWithId.setlastName("Mueller");
        customerWithId.setcity("Chicago");
        customerWithId.setstreet("Fake Street");
        customerWithId.setzip("60606");
        customerWithId.setemail("danmuller@gmail.com");
        customerWithId.setphone("7732025000");
        assertEquals(customerWithId, service.getCustomerById(1));
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
        doReturn(productWithId).when(productClient).getProduct(1);
        doReturn(productList).when(productClient).getAllProducts();
        doReturn(productWithId).when(productClient).saveProduct(product);

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
        doReturn(levelUpWithId).when(levelUpClient).createLevelUp(levelUp);
    }


    private void setUpInvoiceClientMock() {
        invoiceClient = mock(PurchaseClient.class);
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.parse("2019-12-05"));
        Invoice invoiceWithId = new Invoice();
        invoiceWithId.setInvoiceId(1);
        invoiceWithId.setCustomerId(1);
        invoiceWithId.setPurchaseDate(LocalDate.parse("2019-12-05"));
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoiceWithId);
        doReturn(invoiceWithId).when(invoiceClient).getInvoiceById(1);
        doReturn(invoiceList).when(invoiceClient).getAllInvoices();
        doReturn(invoiceList).when(invoiceClient).findInvoicesByCustomerId(1);
        doReturn(invoiceWithId).when(invoiceClient).createInvoice(invoice);
    }

    private void setUpInvoiceItemClientMock() {
        invoiceItemClient = mock(PurchaseClient.class);
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setProductId(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnitPrice(1);
        InvoiceItem invoiceItemWithId = new InvoiceItem();
        invoiceItemWithId.setInvoiceId(1);
        invoiceItemWithId.setProductId(1);
        invoiceItemWithId.setQuantity(1);
        invoiceItemWithId.setUnitPrice(1);
        invoiceItemWithId.setInvoiceItemId(1);
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItemWithId);
//        doReturn(invoiceItemWithId).when((InvoiceItemClient)invoiceItemClient).getInvoiceItemById(1);
//        doReturn(invoiceItemList).when((InvoiceItemClient)invoiceItemClient).getAllInvoiceItems();
//        doReturn(invoiceItemWithId).when((InvoiceItemClient)invoiceItemClient).createInvoiceItem(invoiceItem);
    }

    private void setUpCustomerClientMock() {
        customerClient = mock(CustomerClient.class);
        final Customer customer = new Customer("Dan", "Mueller", "Fake Street", "Chicago", "60606", "danmuller@gmail.com", "7732025000");
        final Customer customerWithId = new Customer(1, "Dan", "Mueller", "Fake Street", "Chicago", "60606", "danmuller@gmail.com", "7732025000");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customerWithId);
        doReturn(customerWithId).when(customerClient).getCustomer(1);
        doReturn(customerList).when(customerClient).getAllCustomers();
        doReturn(customerWithId).when(customerClient).saveCustomer(customer);
    }
}