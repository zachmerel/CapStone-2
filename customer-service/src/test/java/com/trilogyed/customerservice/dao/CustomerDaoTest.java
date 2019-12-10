package com.trilogyed.customerservice.dao;

import com.trilogyed.customerservice.model.Customer;
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
public class CustomerDaoTest {

@Autowired
    private CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
    customerDao.findAll()
            .forEach(customer1 -> customerDao.deleteById(customer1.getCustomerId()));
    }

    @Test
    public void shouldGetListOfCustomersByEmail() throws Exception{
        //arrange
        Customer customerForList = new Customer ("Dan", "Mueller", "Fake Street", "Chicago", "60606", "danmueller@gmail.com", "1231231234");
        customerDao.save(customerForList);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customerForList);

        //act
        List<Customer> customerListIGot = customerDao.findCustomersByEmail("danmueller@gmail.com");

        //assert
        assertEquals(customerList,customerListIGot);

    }
}



