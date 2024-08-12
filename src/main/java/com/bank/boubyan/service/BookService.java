package com.bank.boubyan.service;

import com.bank.boubyan.model.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO getById(Long id);
    List<BookDTO> getAll(Long offset, Long limit);
    Long count();
    void create(BookDTO bookDTO);
    void deleteById(Long id);
    void update(BookDTO bookDTO);
}
