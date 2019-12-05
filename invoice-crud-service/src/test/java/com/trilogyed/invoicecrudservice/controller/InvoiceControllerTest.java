package com.trilogyed.invoicecrudservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.invoicecrudservice.dao.InvoiceRepo;
import com.trilogyed.invoicecrudservice.dto.Invoice;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InvoiceRepo invoiceRepo;
    private JacksonTester<Invoice> invoiceJacksonTester;
    private JacksonTester<List<Invoice>> invoiceListJacksonTester;
    private Invoice invoice;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
        invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.parse("2019-12-05"));

    }

    @Test
    public void shouldGetAllInvoice() throws Exception {
        invoice.setInvoiceId(1);
        given(invoiceRepo.findAll())
                .willReturn(new ArrayList<Invoice>() {
                    {
                        add(invoice);
                    }
                });

        MockHttpServletResponse response = mockMvc.perform(
                get("/invoice")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        List<Invoice> invoices = new ArrayList<Invoice>() {
            {
                add(invoice);
            }
        };

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(invoiceListJacksonTester.write(invoices).getJson());
    }

    @Test
    public void shouldGetInvoiceById() throws Exception {
        invoice.setInvoiceId(1);
        Optional<Invoice> optionalInvoice = Optional.of(invoice);
        given(invoiceRepo.findById(1))
                .willReturn(optionalInvoice);

        MockHttpServletResponse response = mockMvc.perform(
                get("/invoice/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                invoiceJacksonTester.write(invoice).getJson());
    }

    @Test
    public void shouldCreateUpdateAndDeleteInvoice() throws Exception {
        Invoice invoiceAdded = invoice;
        invoiceAdded.setInvoiceId(1);

        given(invoiceRepo.save(invoice)).willReturn(invoiceAdded);
        Optional<Invoice> optionalInvoiceAdded = Optional.of(invoice);
        given(invoiceRepo.findById(1)).willReturn(optionalInvoiceAdded);
        MockHttpServletResponse createResponse = mockMvc.perform(
                post("/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invoiceJacksonTester
                                .write(invoice)
                                .getJson()))
                .andReturn().getResponse();
        assertThat(createResponse.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(createResponse.getContentAsString()).isEqualTo(invoiceJacksonTester.write(invoiceAdded).getJson());

        Invoice invoice2 = invoice;
        //update invoice
        //invoice2.setTitle("new title");
        MockHttpServletResponse updateResponse = mockMvc.perform(
                put("/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invoiceJacksonTester
                                .write(invoice2)
                                .getJson()))
                .andReturn().getResponse();

        assertThat(updateResponse.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());

        MockHttpServletResponse deleteResponse = mockMvc.perform(
                delete("/invoice/1"))
                .andReturn().getResponse();

        assertThat(deleteResponse.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void shouldReturn422WhenInvalidInput() throws Exception {

        MockHttpServletResponse addEmptyStringResponse = mockMvc.perform(
                post("/invoice").contentType(MediaType.APPLICATION_JSON)
                        .content(invoiceJacksonTester.write(new Invoice()).getJson())
        ).andReturn().getResponse();

        assertThat(addEmptyStringResponse.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());

        MockHttpServletResponse addNullResponse = mockMvc.perform(
                post("/invoice").contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(addNullResponse.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @Test
    public void shouldReturn404WhenIdIsInvalid() throws Exception {

        MockHttpServletResponse deleteResponse = mockMvc.perform(
                delete("/invoice/10"))
                .andReturn().getResponse();

        assertThat(deleteResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        MockHttpServletResponse response = mockMvc.perform(
                get("/invoice/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
//
//    @Test
//    public void shouldGetAllInvoicesByGroups() throws Exception {
//        invoice.setInvoiceId(1);
//        List<Invoice> invoices=new ArrayList<Invoice>();
//        invoices.add(invoice);
//        given(invoiceRepo.findAllsByRating("bad")).willReturn(invoices);
//        given(invoiceRepo.findAllsByStudio("Studio 1")).willReturn(invoices);
//        given(invoiceRepo.findAllsByTitle("invoice")).willReturn(invoices);
//
//        MockHttpServletResponse response = mockMvc.perform(
//                get("/invoice/studio/Studio 1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(invoiceListJacksonTester.write(invoices).getJson());
//
//        response = mockMvc.perform(
//                get("/invoice/title/invoice")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(invoiceListJacksonTester.write(invoices).getJson());
//        response = mockMvc.perform(
//                get("/invoice/rating/bad")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(invoiceListJacksonTester.write(invoices).getJson());
//    }
}