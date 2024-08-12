package com.bank.boubyan.model.mapper;

import com.bank.boubyan.model.domain.Book;
import com.bank.boubyan.model.domain.BookDomain;
import com.bank.boubyan.model.dto.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<Book, BookDTO> {
    @Override
    public Book toEntity(BookDTO dto) {
        var book = new Book();
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setDomain(dto.getDomain()!= null ? dto.getDomain(): BookDomain.OTHER);
        book.setPagesCount(dto.getPagesCount());
        book.setPublisher(dto.getPublisher());
        book.setPrintingNumber(dto.getPrintingNumber());
        return book;
    }
    @Override
    public BookDTO toDTO(Book entity) {
        var bookDTO = new BookDTO();
        bookDTO.setId(entity.getId());
        bookDTO.setAuthor(entity.getAuthor());
        bookDTO.setTitle(entity.getTitle());
        bookDTO.setDescription(entity.getDescription());
        bookDTO.setDomain((entity.getDomain() != null)? entity.getDomain(): BookDomain.OTHER);
        bookDTO.setPagesCount(entity.getPagesCount());
        bookDTO.setPublisher(entity.getPublisher());
        bookDTO.setPrintingNumber(entity.getPrintingNumber());
        bookDTO.setCreatedAt(entity.getCreatedAt());
        bookDTO.setCreatedBy(entity.getCreatedBy());
        bookDTO.setUpdatedAt(bookDTO.getUpdatedAt());
        bookDTO.setUpdatedBy(bookDTO.getUpdatedBy());
        return bookDTO;
    }
    public void toEntity(Book book, BookDTO dto) {
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setPagesCount(dto.getPagesCount());
        book.setDescription(dto.getDescription() != null? dto.getDescription(): book.getDescription());
        book.setDomain(dto.getDomain() != null ? dto.getDomain(): book.getDomain());
        book.setPublisher((dto.getPublisher()!= null)? dto.getPublisher() : book.getPublisher());
        book.setPrintingNumber(dto.getPrintingNumber()!= null? dto.getPrintingNumber(): book.getPrintingNumber());
    }
}
