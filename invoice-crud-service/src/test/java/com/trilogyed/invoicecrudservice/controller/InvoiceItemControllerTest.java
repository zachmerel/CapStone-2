package com.trilogyed.invoicecrudservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.invoicecrudservice.dao.InvoiceItemRepo;
import com.trilogyed.invoicecrudservice.dto.InvoiceItem;
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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceItemController.class)
public class InvoiceItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InvoiceItemRepo invoiceItemRepo;
    private JacksonTester<InvoiceItem> invoiceItemJacksonTester;
    private JacksonTester<List<InvoiceItem>> invoiceItemListJacksonTester;
    private InvoiceItem invoiceItem;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
        invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setProductId(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnitPrice(1);

    }

    @Test
    public void shouldGetAllInvoiceItem() throws Exception {
        invoiceItem.setInvoiceItemId(1);
        given(invoiceItemRepo.findAll())
                .willReturn(new ArrayList<InvoiceItem>() {
                    {
                        add(invoiceItem);
                    }
                });

        MockHttpServletResponse response = mockMvc.perform(
                get("/invoiceItem")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>() {
            {
                add(invoiceItem);
            }
        };

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(invoiceItemListJacksonTester.write(invoiceItems).getJson());
    }

    @Test
    public void shouldGetInvoiceItemById() throws Exception {
        invoiceItem.setInvoiceItemId(1);
        Optional<InvoiceItem> optionalInvoiceItem = Optional.of(invoiceItem);
        given(invoiceItemRepo.findById(1))
                .willReturn(optionalInvoiceItem);

        MockHttpServletResponse response = mockMvc.perform(
                get("/invoiceItem/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                invoiceItemJacksonTester.write(invoiceItem).getJson());
    }

    @Test
    public void shouldCreateUpdateAndDeleteInvoiceItem() throws Exception {
        InvoiceItem invoiceItemAdded = invoiceItem;
        invoiceItemAdded.setInvoiceItemId(1);

        given(invoiceItemRepo.save(invoiceItem)).willReturn(invoiceItemAdded);
        Optional<InvoiceItem> optionalInvoiceItemAdded = Optional.of(invoiceItem);
        given(invoiceItemRepo.findById(1)).willReturn(optionalInvoiceItemAdded);
        MockHttpServletResponse createResponse = mockMvc.perform(
                post("/invoiceItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invoiceItemJacksonTester
                                .write(invoiceItem)
                                .getJson()))
                .andReturn().getResponse();
        assertThat(createResponse.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(createResponse.getContentAsString()).isEqualTo(invoiceItemJacksonTester.write(invoiceItemAdded).getJson());

        InvoiceItem invoiceItem2 = invoiceItem;
        //update invoiceItem
        //invoiceItem2.setTitle("new title");
        MockHttpServletResponse updateResponse = mockMvc.perform(
                put("/invoiceItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invoiceItemJacksonTester
                                .write(invoiceItem2)
                                .getJson()))
                .andReturn().getResponse();

        assertThat(updateResponse.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());

        MockHttpServletResponse deleteResponse = mockMvc.perform(
                delete("/invoiceItem/1"))
                .andReturn().getResponse();

        assertThat(deleteResponse.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void shouldReturn422WhenInvalidInput() throws Exception {

        MockHttpServletResponse addEmptyStringResponse = mockMvc.perform(
                post("/invoiceItem").contentType(MediaType.APPLICATION_JSON)
                        .content(invoiceItemJacksonTester.write(new InvoiceItem()).getJson())
        ).andReturn().getResponse();

        assertThat(addEmptyStringResponse.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());

        MockHttpServletResponse addNullResponse = mockMvc.perform(
                post("/invoiceItem").contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(addNullResponse.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @Test
    public void shouldReturn404WhenIdIsInvalid() throws Exception {

        MockHttpServletResponse deleteResponse = mockMvc.perform(
                delete("/invoiceItem/10"))
                .andReturn().getResponse();

        assertThat(deleteResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        MockHttpServletResponse response = mockMvc.perform(
                get("/invoiceItem/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
//
//    @Test
//    public void shouldGetAllInvoiceItemsByGroups() throws Exception {
//        invoiceItem.setInvoiceItemId(1);
//        List<InvoiceItem> invoiceItems=new ArrayList<InvoiceItem>();
//        invoiceItems.add(invoiceItem);
//        given(invoiceItemRepo.findAllsByRating("bad")).willReturn(invoiceItems);
//        given(invoiceItemRepo.findAllsByStudio("Studio 1")).willReturn(invoiceItems);
//        given(invoiceItemRepo.findAllsByTitle("invoiceItem")).willReturn(invoiceItems);
//
//        MockHttpServletResponse response = mockMvc.perform(
//                get("/invoiceItem/studio/Studio 1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(invoiceItemListJacksonTester.write(invoiceItems).getJson());
//
//        response = mockMvc.perform(
//                get("/invoiceItem/title/invoiceItem")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(invoiceItemListJacksonTester.write(invoiceItems).getJson());
//        response = mockMvc.perform(
//                get("/invoiceItem/rating/bad")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(invoiceItemListJacksonTester.write(invoiceItems).getJson());
//    }
}