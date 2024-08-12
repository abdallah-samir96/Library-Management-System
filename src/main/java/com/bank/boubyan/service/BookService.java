package com.bank.boubyan.service;

import com.bank.boubyan.model.dto.BookDTO;

public interface BookService {
    BookDTO getById(Long id);
}
