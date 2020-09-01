//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.01 at 03:41:25 AM MSK 
//


package com.aleksandr0412.spring_ws.bookstore;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.aleksandr0412.spring_ws.bookstore package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.aleksandr0412.spring_ws.bookstore
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBookByIdResponse }
     */
    public GetBookByIdResponse createGetBookByIdResponse() {
        return new GetBookByIdResponse();
    }

    /**
     * Create an instance of {@link Book }
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link GetAllBooksRequest }
     */
    public GetAllBooksRequest createGetAllBooksRequest() {
        return new GetAllBooksRequest();
    }

    /**
     * Create an instance of {@link GetBookByIdRequest }
     */
    public GetBookByIdRequest createGetBookByIdRequest() {
        return new GetBookByIdRequest();
    }

    /**
     * Create an instance of {@link GetAllBooksResponse }
     */
    public GetAllBooksResponse createGetAllBooksResponse() {
        return new GetAllBooksResponse();
    }

}
