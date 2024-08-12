package com.bank.boubyan.service.impl;

import com.bank.boubyan.exception.BookNotFoundException;
import com.bank.boubyan.model.domain.Book;
import com.bank.boubyan.model.dto.BookDTO;
import com.bank.boubyan.model.mapper.BookMapper;
import com.bank.boubyan.repository.BookRepository;
import com.bank.boubyan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = mapper;
    }
    @Override
    public BookDTO getById(Long id) {
        var bookOptional = getBookById(id);
        if(bookOptional.isPresent()) {
            return bookMapper.toDTO(bookOptional.get());
        }
        throw new BookNotFoundException("Book With Id = " + id + " Is Not Found!!", HttpStatus.NOT_FOUND.value());
    }
    private Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    @Override
    public List<BookDTO> getAll(Long offset, Long limit) {
        var pageable = PageRequest.of((int)(offset/ limit), limit.intValue());
        var result = bookRepository.findAll(pageable);
        return bookMapper.toDTO(result.stream().toList());
    }
    @Override
    public Long count() {
        return bookRepository.count();
    }
    @Override
    public void create(BookDTO bookDTO) {
        var book = bookMapper.toEntity(bookDTO);
        bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        // this call used to validate existing of the Book
        var book = getById(id);
        Logger.getLogger(this.getClass().getName()).info(book.toString());
        bookRepository.deleteById(id);
    }
    @Override
    public void update(BookDTO bookDTO) {
        var bookOptional = getBookById(bookDTO.getId());
        if(bookOptional.isEmpty()) { throw new BookNotFoundException("Book With Id = " + bookDTO.getId() + " Is Not Found!!", HttpStatus.NOT_FOUND.value());}
        bookMapper.toEntity(bookOptional.get(), bookDTO);
        bookRepository.save(bookOptional.get());
    }
}
