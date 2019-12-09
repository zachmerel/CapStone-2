package com.trilogyed.retailedgeservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.validation.constraints.NotEmpty;
import java.util.Objects;


public class Customer {

    private Integer customerId;
    @NotEmpty(message = "must provide first name of customer")
    private String firstName;
    @NotEmpty(message = "must provide last name of customer")
    private String lastName;
    @NotEmpty(message = "must provide a street for address")
    private String street;
    @NotEmpty(message = "must provide a city for address")
    private String city;
    @NotEmpty(message = "must provide a zip code for address")
    private String zip;
    @NotEmpty(message = "must provide a email for address")
    private String email;
    @NotEmpty(message = "must provide a phone number")
    private String phone;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String street, String city, String zip, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    }

    public Customer(Integer CustomerId, String firstName, String lastName, String street, String city, String zip, String email, String phone) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getstreet() {
        return street;
    }

    public void setstreet(String street) {
        this.street = street;
    }

    public String getcity() {
        return city;
    }

    public void setcity(String city) {
        this.city = city;
    }

    public String getzip() {
        return zip;
    }

    public void setzip(String zip) {
        this.zip = zip;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(street, customer.street) &&
                Objects.equals(city, customer.city) &&
                Objects.equals(zip, customer.zip) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phone, customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, street, city, zip, email, phone);
    }
}
