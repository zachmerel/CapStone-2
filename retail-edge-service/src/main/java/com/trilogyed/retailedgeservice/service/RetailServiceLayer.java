package com.trilogyed.retailedgeservice.service;

import com.trilogyed.retailedgeservice.dto.*;
import com.trilogyed.retailedgeservice.exceptions.MultipleCustomersException;
import com.trilogyed.retailedgeservice.feign.*;
import com.trilogyed.retailedgeservice.view.CustomerViewModel;
import com.trilogyed.retailedgeservice.view.InvoiceViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RetailServiceLayer {
    private CustomerClient customerClient;
    private PurchaseClient invoiceClient;
    private PurchaseClient invoiceItemClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;
    public static final String EXCHANGE = "queue-levelUp-exchange";
    public static final String ROUTING_KEY = "levelup.points.add.create";
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RetailServiceLayer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    public RetailServiceLayer(CustomerClient customerClient, PurchaseClient invoiceClient, PurchaseClient invoiceItemClient, LevelUpClient levelUpClient, ProductClient productClient) {
        this.customerClient = customerClient;
        this.invoiceClient = invoiceClient;
        this.invoiceItemClient = invoiceItemClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }

//    public InvoiceViewModel submitInvoice(Invoice invoice) {
//        if (invoice.getInvoiceId() == 0) {
//            invoice = createInvoice(invoice);
//        }
//        //now it has an id
//        InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
//        int totalPrice = 0;
//        for (InvoiceItem invoiceItem : ivm.getInvoiceItems()) {
//            totalPrice += invoiceItem.getUnitPrice() * invoiceItem.getQuantity();
//        }
//        LevelUp levelUp = findLevelUpByCustomerId(ivm.getCustomer().getCustomerId()).get(1);
//        levelUp.setPoints(levelUp.getPoints() + 10 * (totalPrice / 50));
//        updateLevelUp(levelUp);
//        return ivm;
//    }

    public InvoiceViewModel order(String email, Map<Integer, Integer> itemQuantityMap) {
        Invoice invoice = new Invoice();
        int customerId = getCustomerByEmail(email).get(0).getCustomerId();
        invoice.setCustomerId(customerId);
        invoice.setPurchaseDate(LocalDate.now());
        invoice = createInvoice(invoice);
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        for (Integer productId : itemQuantityMap.keySet()) {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoiceId(invoice.getInvoiceId());
            Product product = getProductById(productId);
            invoiceItem.setProductId(productId);
            int quantity = itemQuantityMap.get(productId);
            if (quantity > 0 && quantity <= product.getinventory()) {
                invoiceItem.setQuantity(quantity);
            } else {
                throw new IllegalArgumentException("You have entered and invalid order quantity. Please enter a positive integer less than or equal to " + product.getinventory());
            }
            invoiceItem.setUnitPrice(product.getlist_price());
            invoiceItems.add(createInvoiceItem(invoiceItem));
        }
        InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
        int totalPrice = 0;
        for (InvoiceItem invoiceItem : ivm.getInvoiceItems()) {
            totalPrice += invoiceItem.getUnitPrice() * invoiceItem.getQuantity();
        }
        LevelUp levelUp;
        do{levelUp= findLevelUpByCustomerId(ivm.getCustomer().getCustomerId());}
        while(levelUp==null);
        levelUp.setPoints(levelUp.getPoints() + 10 * (totalPrice / 50));
        updateLevelUp(levelUp);
        return ivm;
    }

    public Invoice createInvoice(Invoice invoice) {
        return invoiceClient.createInvoice(invoice);
    }

    public Invoice getInvoiceById(int id) {
        return invoiceClient.getInvoiceById(id);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceClient.getAllInvoices();
    }

    public List<Invoice> getInvoicesByCustomerId(int customerId) {
        return invoiceClient.findInvoicesByCustomerId(customerId);
    }

    public List<Product> getProductsInInventory() {
        return productClient.getAllProducts();
    }

    public Product getProductById(int id) {
        return productClient.getProductById(id);
    }


    public List<Product> getProductByInvoiceId(int id) {
        List<Product> products = new ArrayList<>();
        findInvoiceItemsByInvoiceId(id).forEach(x ->
                products.add(
                        productClient.getProductById(
                                x.getProductId()
                        )));
        return products;
    }

    private List<InvoiceItem> findInvoiceItemsByInvoiceId(int id) {
        return invoiceItemClient.findInvoiceItemsByInvoiceId(id);
    }

    public Integer getLevelUpPointsByCustomerId(int id) {
        LevelUp levelUp = findLevelUpByCustomerId(id);
        if (levelUp==null){
            levelUp=new LevelUp();
            levelUp.setMemberDate(LocalDate.now());
            levelUp.setCustomerId(id);
            createLevelUp(levelUp);
            return 0;
        }
        return levelUp.getPoints();
    }

    public LocalDate getMemberDateByCustomerId(int id) {
        LevelUp levelUp = findLevelUpByCustomerId(id);
        if (levelUp==null){
            levelUp=new LevelUp();
            levelUp.setMemberDate(LocalDate.now());
            levelUp.setCustomerId(id);
            createLevelUp(levelUp);
            return LocalDate.now();
        }
        return levelUp.getMemberDate();
    }

    public LevelUp findLevelUpByCustomerId(int id) {
        List<LevelUp> levelUps= levelUpClient.findLevelUpByCustomerId(id);
        if(levelUps.size()==1){
            return levelUps.get(0);
        } else if (levelUps.size() > 1) {
            throw new MultipleCustomersException("There were multiple LevelUp accounts associated with customerId " + id);
        }else{
            return null;
        }
    }

    InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setPurchaseDate(invoice.getPurchaseDate());
        ivm.setCustomer(buildCustomerViewModel(customerClient.getCustomer(invoice.getCustomerId())));
        int id = invoice.getInvoiceId();
        ivm.setId(id);
        if (ivm.getInvoiceItems() == null) {
            ivm.setInvoiceItems(findInvoiceItemsByInvoiceId(id));
        }
        return ivm;
    }


    private Customer getCustomer(int id) {
        return customerClient.getCustomer(id);
    }

    CustomerViewModel buildCustomerViewModel(Customer customer) {
        CustomerViewModel cvm = new CustomerViewModel();
        int id = customer.getCustomerId();
        cvm.setCity(customer.getcity());
        cvm.setCustomerId(id);
        cvm.setEmail(customer.getemail());
        cvm.setPhone(customer.getphone());
        cvm.setFirstName(customer.getfirstName());
        cvm.setLastName(customer.getlastName());
        cvm.setStreet(customer.getstreet());
        cvm.setZip(customer.getzip());
        cvm.setPoints(getLevelUpPointsByCustomerId(id));
        cvm.setMemberDate(getMemberDateByCustomerId(id));
        return cvm;

    }

    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemClient.createInvoiceItem(invoiceItem);
    }

    private List<Customer> getCustomerByEmail(String email) {
        return customerClient.findCustomersByEmail(email);
    }

    //CUSTOMER
    public Customer getCustomerById(int id) {
        return customerClient.getCustomer(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerClient.saveCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerClient.getAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        customerClient.updateCustomer(customer);
    }

    public void deleteCustomerById(int id) {
        customerClient.deleteCustomer(id);
    }

    //PRODUCTS
    public Product createProduct(Product product) {
        return productClient.createProduct(product);
    }

    public void updateProductInventory(Inventory inventory) {
        productClient.updateProductInventory(inventory);
    }

//    public List<Product> getAllProducts() {
//        return productClient.getAllProducts();
//    }

    public void updateProduct(Product product) {
        productClient.updateProduct(product);
    }

    public void deleteProductById(int id) {
        productClient.deleteProductById(id);
    }

    //INVOICE

    public void deleteInvoiceById(int id) {
        invoiceClient.deleteInvoiceById(id);
    }

    public void updateInvoice(Invoice invoice) {
        invoiceClient.updateInvoice(invoice);
    }

    //LEVEL UPS

    public LevelUp getLevelUpById(int id) {
        return levelUpClient.getLevelUpById(id);
    }

    public List<LevelUp> getAllLevelUps() {
        return levelUpClient.getAllLevelUps();
    }

    public void deleteLevelUpById(int id) {
        levelUpClient.deleteLevelUpById(id);
    }

    public void updateLevelUp(LevelUp levelUp) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, levelUp);
        System.out.println("Message Sent");
    }

    public InvoiceItem getInvoiceItemById(int id) {
        return invoiceItemClient.getInvoiceItemById(id);
    }

    public void deleteInvoiceItemById(int id) {
        invoiceItemClient.deleteInvoiceItemById(id);
    }

    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItemClient.updateInvoiceItem(invoiceItem);
    }

    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemClient.getAllInvoiceItems();
    }

    public void createLevelUp(LevelUp levelUp) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, levelUp);
        System.out.println("Message Sent");
    }
}
