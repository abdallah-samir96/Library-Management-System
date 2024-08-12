package com.bank.boubyan.model.dto;

import java.time.LocalDateTime;

public record ErrorDTO(String message, String errorCode, String path, LocalDateTime timestamp) {
}
