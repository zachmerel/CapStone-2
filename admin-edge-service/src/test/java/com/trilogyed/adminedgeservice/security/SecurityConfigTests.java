package com.trilogyed.adminedgeservice.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigTests {


    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void givenAdminUserNameAndPassWordToGetAllCustomers_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "admin", "adminPassword").getForEntity("/customer",
                String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void givenManagerUserNameAndPassWordToGetAllProducts_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "manager", "managerPassword").getForEntity("/product",
                String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void givenEmployeeUserNameAndPassWordToGetAllLevelUps_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "employee", "employeePassword").getForEntity("/levelUp",
                String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void givenEmployeeUserNameAndPassWordToGetAllInvoices_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "employee", "employeePassword").getForEntity("/invoice",
                String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    //"GET BY ID" END POINTS
    @Test
    public void givenAdminUserNameAndPassWordToGetCustomerById_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "admin", "adminPassword").getForEntity("/customer/{id}",
                String.class,1);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void givenManagerUserNameAndPassWordToGetProductById_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "manager", "managerPassword").getForEntity("/product/{id}",
                String.class,1);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void givenEmployeeUserNameAndPassWordToGetLevelUpById_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "employee", "employeePassword").getForEntity("/levelUp/{id}",
                String.class,1);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void givenEmployeeUserNameAndPassWordToGetInvoiceById_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "employee", "employeePassword").getForEntity("/invoice/{id}",
                String.class,1);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

//    @Test
//    public void givenAdminUserNameAndPassWordToUpdateCustomer_shouldSucceedWith200() throws Exception {
//        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
//                "admin", "adminPassword").put("/customer",
//                String.class);
//
//        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
//    }

    @Test
    public void givenAdminUserNameAndPassWordDeleteCustomerById_shouldSucceedWith200() throws Exception {
        testRestTemplate.withBasicAuth(
                "admin", "adminPassword").delete("/customer/{id}",
                String.class,1);

        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "employee", "employeePassword").getForEntity("/customer/{id}",
                String.class,1);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }



    //Testing for fail
    @Test
    public void givenAdminUserNameAndWrongPassWord_shouldFailWith401() throws Exception {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "admin", "notThePassword").getForEntity("/customer",
                String.class,1);


        assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
    }

}