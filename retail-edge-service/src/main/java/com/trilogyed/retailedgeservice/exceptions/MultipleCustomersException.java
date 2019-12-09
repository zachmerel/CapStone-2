package com.trilogyed.retailedgeservice.exceptions;

public class MultipleCustomersException extends RuntimeException {
    public MultipleCustomersException(String message) {
        super(message);
    }
}
