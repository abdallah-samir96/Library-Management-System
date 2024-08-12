package com.bank.boubyan.resource.v1;

import com.bank.boubyan.model.dto.BookDTO;
import com.bank.boubyan.model.dto.LibraryMSResponse;
import com.bank.boubyan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/{id}")
    ResponseEntity<LibraryMSResponse<BookDTO>> getById(@PathVariable("id") Long id) {
        var data = bookService.getById(id);
        var response = new LibraryMSResponse.Builder<BookDTO>()
                .data(data)
                .moreDetails("Getting By ID," + this.getClass().getName())
                .build();
        return ResponseEntity.ok(response);
    }
}
