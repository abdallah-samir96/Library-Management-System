package com.bank.boubyan.resource.v1;

import com.bank.boubyan.model.dto.BookDTO;
import com.bank.boubyan.model.dto.LibraryMSResponse;
import com.bank.boubyan.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    ResponseEntity<LibraryMSResponse<List<BookDTO>>> getAll(
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "Offset Should be > 0") Long offset,
            @RequestParam(defaultValue = "25") @Min(value = 0, message = "Limit Should be > 0") Long limit
            ) {
        var data = bookService.getAll(offset, limit);
        var count = bookService.count();
        var response = new LibraryMSResponse.Builder<List<BookDTO>>()
                .data(data)
                .totalCount(count)
                .offset(offset)
                .limit(limit)
                .moreDetails("Getting All Books Info")
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public void create(@RequestBody @Valid BookDTO bookDTO) {
         bookService.create(bookDTO);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
    @PutMapping("/update")
    public void update(@RequestBody @Valid BookDTO bookDTO) {
        bookService.update(bookDTO);
    }
}