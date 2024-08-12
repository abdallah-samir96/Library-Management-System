package com.bank.boubyan.service.impl;

import com.bank.boubyan.exception.BookNotFoundException;
import com.bank.boubyan.model.dto.BookDTO;
import com.bank.boubyan.model.mapper.BookMapper;
import com.bank.boubyan.repository.BookRepository;
import com.bank.boubyan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public BookDTO getById(Long id) {
        var bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()) {
            return new BookMapper().toDTO(bookOptional.get());
        }
        throw new BookNotFoundException("Book With Id = " + id + " Is Not Found!!", HttpStatus.NOT_FOUND.value());
    }
}
