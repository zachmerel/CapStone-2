package com.trilogyed.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.productservice.dao.ProductDao;
import com.trilogyed.productservice.exception.NotFoundException;
import com.trilogyed.productservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ProductDao productDao;
    private static final Product Product_NO_ID = new Product("Water Bottle", "28oz water bottle",9.99,5.00);
    private static final Product Product_ID = new Product(1,"Water Bottle", "28oz water bottle",9.99,5.00);
    private static final List<Product> Product_LIST = new ArrayList<>(Arrays.asList(Product_ID));
    private static final Product Product_UPDATED = new Product(1, "Water Bottle", "40oz water bottle",9.99,5.00);
    private static final Product Product_BAD_UPDATE = new Product(7, "Water Bottle", "28oz water bottle",9.99,5.00);
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUpMock() {
        when(productDao.save(Product_NO_ID)).thenReturn(Product_ID);
        when(productDao.getOne(1)).thenReturn(Product_ID);
        when(productDao.findAll()).thenReturn(Product_LIST);
        //success and failure messages sent from service layer if applicable
        //when(productDao.updateProduct(Product_UPDATED)).thenReturn("Update: "+ SUCCESS);
        //when(productDao.deleteProduct(1)).thenReturn("Delete: " + SUCCESS);
        //when(productDao.updateProduct(Product_BAD_UPDATE)).thenReturn("Update: "+ FAIL);
        //when(productDao.deleteProduct(1)).thenReturn("Delete: " + FAIL);
        //exceptions
        when(productDao.save(Product_BAD_UPDATE)).thenThrow(new NotFoundException("bad thing"));
        doThrow(new NotFoundException("Product is not found")).when(productDao).deleteById(7);
    }

    @Test
    public void saveProduct() throws Exception {
        String input_json = mapper.writeValueAsString(Product_NO_ID);
        String output_json = mapper.writeValueAsString(Product_ID);
        mvc.perform(post("/product")
                .content(input_json)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(output_json));
    }

    @Test
    public void getProduct() throws Exception {
        String output_json = mapper.writeValueAsString(Product_ID);
        mvc.perform(get("/product/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output_json));
    }

    @Test
    public void getAllProducts() throws Exception

    {
        String output_json = mapper.writeValueAsString(Product_LIST);
        mvc.perform(get("/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output_json));
    }

    @Test
    public void updateProduct() throws Exception {
        String input_json = mapper.writeValueAsString(Product_UPDATED);
        mvc.perform(put("/product")
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
    public void deleteProduct() throws Exception {
        mvc.perform(delete("/product/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    //exception test

    @Test
    public void shouldReturnNotFoundWhenUpdateProductNonExistentId() throws Exception {
        String input_json = mapper.writeValueAsString(Product_BAD_UPDATE);
        mvc.perform(put("/product")
                .content(input_json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("bad thing")));
    }

}