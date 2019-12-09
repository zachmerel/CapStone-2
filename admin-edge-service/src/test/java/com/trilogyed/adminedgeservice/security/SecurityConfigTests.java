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
    public void givenAdminUserNameAndWrongPassWord_shouldFailWith401() throws Exception {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth(
                "admin", "notThePassword").getForEntity("/customer",
                String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
    }

}