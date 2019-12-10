package com.trilogyed.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.customerservice.dao.CustomerDao;
import com.trilogyed.customerservice.exception.NotFoundException;
import com.trilogyed.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomerDao customerDao;
    private JacksonTester<Customer> invoiceJacksonTester;
    private JacksonTester<List<Customer>> invoiceListJacksonTester;
    private static final Customer Customer_NO_ID = new Customer("Dan", "Mueller", "Fake Street", "Chicago", "60606", "danmuller@gmail.com", "7732025000");
    private static final Customer Customer_ID = new Customer(1,"Dan", "Mueller", "Fake Street", "Chicago", "60606", "danmuller@gmail.com", "7732025000");
    private static final List<Customer> Customer_LIST = new ArrayList<>(Arrays.asList(Customer_ID));
    private static final Customer Customer_UPDATED = new Customer(1,"Dan", "Mueller", "Updated Street", "Chicago", "60606", "danmuller@gmail.com", "7732025000");
    private static final Customer Customer_BAD_UPDATE = new Customer(7,"Dan", "Mueller", "Fake Street", "Chicago", "60606", "danmuller@gmail.com", "7732025000");
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUpMock() {
        when(customerDao.save(Customer_NO_ID)).thenReturn(Customer_ID);
        when(customerDao.getOne(1)).thenReturn(Customer_ID);
        when(customerDao.findAll()).thenReturn(Customer_LIST);
        when(customerDao.findCustomersByEmail("danmuller@gmail.com")).thenReturn(Customer_LIST);
        //success and failure messages sent from service layer if applicable
//        when(customerDao.save(Customer_UPDATED)).thenReturn("Update: "+ SUCCESS);
//        when(customerDao.deleteById(1)).thenReturn("Delete: " + SUCCESS);
//        when(customerDao.save(Customer_BAD_UPDATE)).thenReturn("Update: "+ FAIL);
//        when(customerDao.deleteById(1)).thenReturn("Delete: " + FAIL);
//        exceptions
        when(customerDao.save(Customer_BAD_UPDATE)).thenThrow(new NotFoundException("bad thing"));
        doThrow(new NotFoundException("Customer not found")).when(customerDao).deleteById(7);
    }

    @Test
    public void saveCustomer() throws Exception {
        String input_json = mapper.writeValueAsString(Customer_NO_ID);
        String output_json = mapper.writeValueAsString(Customer_ID);
        mvc.perform(post("/customer")
                .content(input_json)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(output_json));
    }

    @Test
    public void getCustomer() throws Exception {
        String output_json = mapper.writeValueAsString(Customer_ID);
        mvc.perform(get("/customer/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output_json));
    }

    @Test
    public void getCustomerByEmail() throws Exception{
        String output_json = mapper.writeValueAsString(Customer_LIST);
        mvc.perform(get("/customer/email/{email}","danmuller@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output_json));
    }

    @Test
    public void getAllCustomers() throws Exception
    {
        String output_json = mapper.writeValueAsString(Customer_LIST);
        mvc.perform(get("/customer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output_json));
    }

    @Test
    public void updateCustomer() throws Exception {
        String input_json = mapper.writeValueAsString(Customer_UPDATED);
        mvc.perform(put("/customer")
                .content(input_json)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
        //for things with random or json parsing errors
        //.andExpect(jsonPath("$.id").value("" + REAL_LOCATION.getId()))
        //.andExpect(jsonPath("$.description").value(REAL_LOCATION.getDescription()))
        //.andExpect(jsonPath("$.location").value(REAL_LOCATION.getLocation()));
    }

    @Test
    public void deleteCustomer() throws Exception {
        mvc.perform(delete("/customer/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    //exception test
    @Test
    public void shouldReturnNotFoundWhenUpdateCustomerNonExistentId() throws Exception {
        String input_json = mapper.writeValueAsString(Customer_BAD_UPDATE);
        mvc.perform(put("/customer")
                .content(input_json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("bad thing")));
    }

//    @Test
//    public void shouldReturn422WhenInvalidInput() throws Exception {
//
//        MockHttpServletResponse addEmptyStringResponse = mvc.perform(
//                post("/customer").contentType(MediaType.APPLICATION_JSON)
//                        .content(invoiceJacksonTester.write(new Customer()).getJson())
//        ).andReturn().getResponse();
//
//        assertThat(addEmptyStringResponse.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
//
//        MockHttpServletResponse addNullResponse = mvc.perform(
//                post("/customer").contentType(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//
//        assertThat(addNullResponse.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
//    }

}