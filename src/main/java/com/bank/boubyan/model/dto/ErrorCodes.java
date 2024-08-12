package com.bank.boubyan.model.dto;

import jakarta.servlet.http.PushBuilder;

public enum ErrorCodes {
    BOOK_NOT_FOUND("1000_000_0001");
    public final String code;
    ErrorCodes(String code) {
        this.code = code;
    }

}
