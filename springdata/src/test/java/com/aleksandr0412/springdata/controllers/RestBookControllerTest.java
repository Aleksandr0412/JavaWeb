package com.aleksandr0412.springdata.controllers;

import com.aleksandr0412.springdata.entities.Book;
import com.aleksandr0412.springdata.entities.Genre;
import com.aleksandr0412.springdata.entities.OrderItem;
import com.aleksandr0412.springdata.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestBookController.class)
@AutoConfigureMockMvc(addFilters = false)
class RestBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    private final Book testBook1 = new Book(1L, "Harry Potter", "Description", Genre.FANTASY, BigDecimal.valueOf(300L), 2000, new ArrayList<OrderItem>());
    private final Book testBook2 = new Book(2L, "LOTR", "Description", Genre.FANTASY, BigDecimal.valueOf(400L), 2010, new ArrayList<OrderItem>());

    //    private List<Book> books = new ArrayList<>();
//
//    @BeforeTestClass
//    public void firstInit() {
//        books.add(testBook1);
//        books.add(testBook2);
//    }
    @BeforeEach
    public void init() {
        Mockito.doReturn(testBook1)
                .when(bookService)
                .findById(testBook1.getId());

        Mockito.doReturn(true)
                .when(bookService)
                .existsById(testBook2.getId());

    }

    @Test
    void getBookById() throws Exception {
        mockMvc.perform(get("/api/v1/books/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Harry Potter")))
                .andExpect(jsonPath("$.genre", is("FANTASY")))
                .andExpect(jsonPath("$.description", is("Description")))
                .andExpect(jsonPath("$.price", is(300)))
                .andExpect(jsonPath("$.publishYear", is(2000)));
    }

    @Test
    void createNewBook() throws Exception {
        mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testBook1)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testBook2)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/v1/books/{id}", testBook2.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllBooks() throws Exception {
        mockMvc.perform(delete("/api/v1/books"))
                .andExpect(status().isOk());
    }
}