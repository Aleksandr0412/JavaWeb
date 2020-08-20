package com.aleksandr0412.springdata.controllers;

import com.aleksandr0412.springdata.entities.Book;
import com.aleksandr0412.springdata.entities.Genre;
import com.aleksandr0412.springdata.exceptions.ResourceNotFoundException;
import com.aleksandr0412.springdata.services.BookService;
import com.aleksandr0412.springdata.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class RestBookController {
    private BookService bookService;
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(required = false) MultiValueMap<String, String> params) {
        BookFilter bookFilter = new BookFilter(params);
        Page<Book> pageBook = bookService.findAll(bookFilter.getSpec(), page - 1, 5);

        Map<String, Object> response = new HashMap<>();
        response.put("book", pageBook);
        Stream.of(Genre.values()).forEach(System.out::println);
        response.put("genres", Genre.values());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createNewBook(@RequestBody Book book) {
        if (book.getId() != null) {
            book.setId(null);
        }
        return bookService.saveOrUpdate(book);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Book updateBook(@RequestBody Book book) {
        if (!bookService.existsById(book.getId())) {
            throw new ResourceNotFoundException(String.format("Книга с id= %d не найдена", book.getId()));
        }
        return bookService.saveOrUpdate(book);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllBooks() {
        bookService.deleteAll();
    }



}
