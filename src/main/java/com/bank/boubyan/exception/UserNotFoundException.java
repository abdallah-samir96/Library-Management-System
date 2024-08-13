package com.bank.boubyan.exception;

public class UserNotFoundException extends RuntimeException{
    private int status;
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
