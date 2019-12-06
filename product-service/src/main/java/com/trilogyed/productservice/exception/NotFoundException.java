package com.trilogyed.productservice.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("Message from NotFoundException: " + message);
    }
}
