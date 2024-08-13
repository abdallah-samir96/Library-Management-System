package com.bank.boubyan.exception.handler;

import com.bank.boubyan.exception.BookNotFoundException;
import com.bank.boubyan.exception.UserNotFoundException;
import com.bank.boubyan.model.dto.ErrorCodes;
import com.bank.boubyan.model.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(HttpServletRequest request, BookNotFoundException exception) {
        var errorDTO = new ErrorDTO(
                exception.getMessage(),
                ErrorCodes.BOOK_NOT_FOUND.code,
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(exception.getStatus())
                .body(errorDTO);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(HttpServletRequest request, UserNotFoundException exception) {
        var errorDTO = new ErrorDTO(
                exception.getMessage(),
                ErrorCodes.USER_NOT_FOUND.code,
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(exception.getStatus())
                .body(errorDTO);
    }
}
