package com.trilogyed.retailedgeservice.view;

import java.time.LocalDate;
import java.util.Objects;

public class CustomerViewModel {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private String email;
    private String phone;
    private int points;
    private LocalDate memberDate;

    public CustomerViewModel() {
    }

    @Override
    public String toString() {
        return "customerViewModel{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", points=" + points +
                ", memberDate=" + memberDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerViewModel that = (CustomerViewModel) o;
        return points == that.points &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(street, that.street) &&
                Objects.equals(city, that.city) &&
                Objects.equals(zip, that.zip) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(memberDate, that.memberDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, street, city, zip, email, phone, points, memberDate);
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDate getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(LocalDate memberDate) {
        this.memberDate = memberDate;
    }
}
