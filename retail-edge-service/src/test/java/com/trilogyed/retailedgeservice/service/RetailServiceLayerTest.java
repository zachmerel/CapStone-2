package com.trilogyed.retailedgeservice.service;

import com.trilogyed.retailedgeservice.dto.*;
import com.trilogyed.retailedgeservice.feign.*;
import com.trilogyed.retailedgeservice.view.CustomerViewModel;
import com.trilogyed.retailedgeservice.view.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

public class RetailServiceLayerTest {
    private CustomerClient customerClient;
    private PurchaseClient invoiceClient;
    private PurchaseClient invoiceItemClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;
    private RetailServiceLayer service;

    @Before
    public void setUp() {
        setUpCustomerClientMock();
        setUpInvoiceClientMock();
        setUpInvoiceItemClientMock();
        setUpLevelUpClientMock();
        setUpProductClientMock();
        service = new RetailServiceLayer(customerClient, invoiceClient, invoiceItemClient, levelUpClient, productClient);
    }

    @Test
    public void shouldCreateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.parse("2019-12-05"));
        Invoice invoiceWithId = new Invoice();
        invoiceWithId.setInvoiceId(1);
        invoiceWithId.setCustomerId(1);
        invoiceWithId.setPurchaseDate(LocalDate.parse("2019-12-05"));
        assertEquals(invoiceWithId, service.createInvoice(invoice));
    }
    @Test
    public void shouldSubmitOrder(){
        Invoice invoiceWithId = new Invoice();
        invoiceWithId.setInvoiceId(1);
        invoiceWithId.setCustomerId(1);
        invoiceWithId.setPurchaseDate(LocalDate.parse("2019-12-05"));
        Map<Integer, Integer> mapToSubmit = new HashMap<>();
        InvoiceItem invoiceItemWithId = new InvoiceItem();
        invoiceItemWithId.setInvoiceId(1);
        invoiceItemWithId.setProductId(1);
        invoiceItemWithId.setQuantity(1);
        invoiceItemWithId.setUnitPrice(1);
        invoiceItemWithId.setInvoiceItemId(1);
        InvoiceViewModel ivm=new InvoiceViewModel();
        CustomerViewModel cvm=new CustomerViewModel();
        Customer customer = new Customer(1,"Dan", "Mueller", "Fake Street", "Chicago", "60606", "danmuller@gmail.com", "7732025000");
        cvm = service.buildCustomerViewModel(customer);


        assertEquals(ivm,service.order("email",mapToSubmit));
    }

    @Test
    public void shouldGetInvoiceById() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.parse("2019-12-05"));
        Invoice invoiceWithId = new Invoice();
        invoiceWithId.setInvoiceId(1);
        invoiceWithId.setCustomerId(1);
        invoiceWithId.setPurchaseDate(LocalDate.parse("2019-12-05"));
        assertEquals(invoiceWithId, service.getInvoiceById(1));
    }

    @Test
    public void shouldGetAllInvoices_optionallyByCustomerId() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.parse("2019-12-05"));
        Invoice invoiceWithId = new Invoice();
        invoiceWithId.setInvoiceId(1);
        invoiceWithId.setCustomerId(1);
        invoiceWithId.setPurchaseDate(LocalDate.parse("2019-12-05"));
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoiceWithId);
        assertEquals(invoiceList, service.getAllInvoices());
        assertEquals(invoiceList, service.getInvoicesByCustomerId(1));
    }

    @Test
    public void shouldGetProductsInInventory() {
        Product productWithId = new Product();
        productWithId.setinventory(1);
        productWithId.setlist_price(1);
        productWithId.setproduct_description("description");
        productWithId.setproduct_name("name");
        productWithId.setunit_price(1);
        productWithId.setProductId(1);
        List<Product> expectedProductsList = new ArrayList<>();
        expectedProductsList.add(productWithId);
        assertEquals(expectedProductsList, service.getProductsInInventory());
    }

//    @Test
//    public void shouldGetProductById() {
//
//    }

    @Test
    public void shouldGetProductsByInvoiceId() {
        Product productWithId = new Product();
        productWithId.setinventory(1);
        productWithId.setlist_price(1);
        productWithId.setproduct_description("description");
        productWithId.setproduct_name("name");
        productWithId.setunit_price(1);
        productWithId.setProductId(1);
        List<Product> expectedProductsList = new ArrayList<>();
        expectedProductsList.add(productWithId);
        assertEquals(expectedProductsList, service.getProductByInvoiceId(1));
    }

    @Test
    public void shouldGetLevelUpPointsByCustomerId() {
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
        assertEquals(Optional.of(levelUpList.get(0).getPoints()),service.getLevelUpPointsByCustomerId(1));
    }


    private void setUpProductClientMock() {
        productClient = mock(ProductClient.class);
        Product product = new Product();
        product.setinventory(1);
        product.setlist_price(1);
        product.setproduct_description("description");
        product.setproduct_name("name");
        product.setunit_price(1);
        Product productWithId = new Product();
        productWithId.setinventory(1);
        productWithId.setlist_price(1);
        productWithId.setproduct_description("description");
        productWithId.setproduct_name("name");
        productWithId.setunit_price(1);
        productWithId.setProductId(1);
        List<Product> productList = new ArrayList<>();
        productList.add(productWithId);
        doReturn(productWithId).when(productClient).getProductById(1);
        doReturn(productList).when(productClient).getAllProducts();
        doReturn(productWithId).when(productClient).createProduct(product);

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
        doReturn(levelUpList).when(levelUpClient).findLevelUpByCustomerId(1);
        doReturn(levelUpWithId).when(levelUpClient).createLevelUp(levelUp);
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
        doReturn(invoiceItemWithId).when(invoiceItemClient).getInvoiceItemById(1);
        doReturn(invoiceItemList).when(invoiceItemClient).getAllInvoiceItems();
        doReturn(invoiceItemList).when(invoiceItemClient).findInvoiceItemsByInvoiceId(1);
        doReturn(invoiceItemWithId).when(invoiceItemClient).createInvoiceItem(invoiceItem);
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