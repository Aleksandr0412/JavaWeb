package com.aleksandr0412.spring_ws.service;

import com.aleksandr0412.spring_ws.bookstore.Book;
import com.aleksandr0412.spring_ws.exceptions.ResourceNotFoundException;
import com.aleksandr0412.spring_ws.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with id: " + id + " not found"));
    }

    public Book saveOrUpdate(Book book) {
        return bookRepository.save(book);
    }

    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }
}