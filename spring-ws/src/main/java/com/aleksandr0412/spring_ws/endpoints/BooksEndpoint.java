package com.aleksandr0412.spring_ws.endpoints;


import com.aleksandr0412.spring_ws.bookstore.GetAllBooksResponse;
import com.aleksandr0412.spring_ws.bookstore.GetBookByIdRequest;
import com.aleksandr0412.spring_ws.bookstore.GetBookByIdResponse;
import com.aleksandr0412.spring_ws.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BooksEndpoint {
    private static final String NAMESPACE_URI = "http://aleksandr0412.com/spring-ws/bookstore";

    private BookService bookService;

    @Autowired
    public BooksEndpoint(BookService bookService) {
        this.bookService = bookService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookByIdRequest")
    @ResponsePayload
    public GetBookByIdResponse getBookById(@RequestPayload GetBookByIdRequest request) {
        GetBookByIdResponse response = new GetBookByIdResponse();
        response.setBook(bookService.findBookById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getAllBooks() {
        GetAllBooksResponse response = new GetAllBooksResponse();
        response.setBook(bookService.findAllBooks());
        return response;
    }
}