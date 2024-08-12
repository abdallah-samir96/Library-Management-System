package com.bank.boubyan.exception;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends RuntimeException{
    private int status;
    public BookNotFoundException(String message) {
        super(message);
    }
    public BookNotFoundException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
