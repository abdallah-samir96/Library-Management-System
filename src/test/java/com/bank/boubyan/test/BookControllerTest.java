package com.bank.boubyan.test;

import com.bank.boubyan.model.domain.Book;
import com.bank.boubyan.model.domain.BookDomain;
import com.bank.boubyan.model.dto.BookDTO;
import com.bank.boubyan.model.mapper.BookMapper;
import com.bank.boubyan.resource.v1.BookResource;
import com.bank.boubyan.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookResource.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private static final String GET_ALL = "/api/v1/books?offset=0&limit=2";
    private static final String GET_BY_ID = "/api/v1/books/1";
    private static final String DELETE_BY_ID = "/api/v1/books/{id}";
    private static final String CREATE_URL = "/api/v1/books";
    private static final String UPDATE_URL = "/api/v1/books/update";

    @Test
    public void getAllBooks() throws Exception {
        List<BookDTO> books = getBooks();
        when(bookService.getAll(0L, 2L)).thenReturn(books);
        when(bookService.count()).thenReturn(2L);

        mockMvc.perform(get(GET_ALL))
                .andExpect(status().isOk())
                .andExpect(content().json(getExpectedResponse()));
    }

    @Test
    public void getById() throws Exception {
        var bookDTO = getBooks().get(0);
        when(bookService.getById(1L)).thenReturn(bookDTO);

        mockMvc.perform(get(GET_BY_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(getSingleResponse()));

    }

    @Test
    public void create() throws Exception {
        var bookDTO = getBooks().get(0);
        bookDTO.setPagesCount(1000);

        Mockito.doNothing().when(bookService).create(bookDTO);
        mockMvc.perform(
                post(CREATE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookDTO))
                )
                .andExpect(status().isOk());
    }
    @Test
    public void update() throws Exception {
        var bookDTO = getBooks().get(0);
        bookDTO.setPagesCount(1000);

        Mockito.doNothing().when(bookService).update(bookDTO);
        mockMvc.perform(
                        MockMvcRequestBuilders.put(UPDATE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(bookDTO))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void deleteById() throws Exception {
        Mockito.doNothing().when(bookService).deleteById(1L);

        mockMvc.perform(delete(DELETE_BY_ID, 1L))
                .andExpect(status().isOk());
    }




    private List<BookDTO> getBooks() {
        var book = new Book();
        book.setId(1L);
        book.setTitle("Introducing Java");
        book.setDescription("Java Language with examples");
        book.setAuthor("Abdallah Samir");
        book.setDomain(BookDomain.HISTORICAL);

        var book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Introducing C And C++");
        book2.setDescription("C, C++ Language with examples");
        book2.setAuthor("Abdallah Samir");
        book2.setDomain(BookDomain.LANGUAGES);
        return new BookMapper().toDTO(List.of(book, book2));
    }

    /**
     * This response is returning from THE APi Itself
     * The response from The API is different from the response in the Service Layer
     * */
    private String getExpectedResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(Map.of(
                "data", Arrays.asList(
                        Map.of("id", 1,
                                "title", "Introducing Java",
                                "description", "Java Language with examples",
                                "author", "Abdallah Samir",
                                "domain", "HISTORICAL"),
                        Map.of("id", 2,
                                "title", "Introducing C And C++",
                                "description", "C, C++ Language with examples",
                                "author", "Abdallah Samir",
                                "domain", "LANGUAGES"
                        )
                ),
                "offset", 0,
                "limit", 2,
                "totalCount", 2,
                "moreDetails", "Getting All Books Info"
        ));
    }

    private String getSingleResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(Map.of(
                "data", getBooks().get(0),

                "moreDetails", "Getting By ID"
        ));
    }

}
