package com.bank.boubyan.resource.v1;

import com.bank.boubyan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookResource.BOOK_RESOURCE)
public class BookResource {
    public static final String BOOK_RESOURCE = "/api/v1/books";

    private final BookService bookService;

    @Autowired
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }
}
