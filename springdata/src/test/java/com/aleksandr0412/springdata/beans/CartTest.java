package com.aleksandr0412.springdata.beans;

import com.aleksandr0412.springdata.entities.Book;
import com.aleksandr0412.springdata.entities.Genre;
import com.aleksandr0412.springdata.entities.OrderItem;
import com.aleksandr0412.springdata.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;

@SpringBootTest
class CartTest {
    @Autowired
    private Cart cart;

    @MockBean
    private BookService bookService;

    private static final int EXPECTED_COUNT = 1;
    private static final int EXPECTED_COUNT_ZERO = 0;
    private static final BigDecimal EXPECTED_TOTALPRICE_500 = new BigDecimal("500.00");
    private static final BigDecimal EXPECTED_TOTALPRICE_ZERO = new BigDecimal("0.00");
    private Book book1 = new Book(1L, "Harry Potter", "Description", Genre.FANTASY, new BigDecimal("300.00"), 2000, null);
    private Book book2 = new Book(2L, "LOTR", "Description", Genre.FANTASTIC, new BigDecimal("500.00"), 2010, null);

    @BeforeEach
    public void setUp() {
        Mockito.doReturn(book1)
                .when(bookService)
                .findById(book1.getId());

        Mockito.doReturn(book2)
                .when(bookService)
                .findById(book2.getId());
    }

    @Test
    void addBookToCart() {
        Assertions.assertAll(
                () -> {
                    cart.addBookToCart(1L);
                    OrderItem orderItemOne = cart.getOrderItems().get(0);

                    Assertions.assertEquals(book1, orderItemOne.getBook());
                    Assertions.assertEquals(EXPECTED_COUNT, orderItemOne.getCount());
                    Assertions.assertEquals(book1.getPrice(), orderItemOne.getPrice());
                    Assertions.assertEquals(book1.getPrice(), cart.getTotalPrice());
                }, () -> {
                    cart.addBookToCart(2L);
                    BigDecimal expectedPrice = book1.getPrice().add(book2.getPrice());
                    OrderItem orderItemOne = cart.getOrderItems().get(0);
                    OrderItem orderItemTwo = cart.getOrderItems().get(1);

                    Assertions.assertEquals(book1, orderItemOne.getBook());
                    Assertions.assertEquals(book2, orderItemTwo.getBook());
                    Assertions.assertEquals(EXPECTED_COUNT, orderItemTwo.getCount());
                    Assertions.assertEquals(book2.getPrice(), orderItemTwo.getPrice());
                    Assertions.assertEquals(expectedPrice, cart.getTotalPrice());
                }
        );
    }

    @Test
    void deleteBookFromCartTest() {
        cart.addBookToCart(1L);
        cart.addBookToCart(2L);
        Assertions.assertAll(
                () -> {
                    cart.deleteBookFromCart(1);
                    Assertions.assertEquals(EXPECTED_TOTALPRICE_500, cart.getTotalPrice());
                    Assertions.assertNotEquals(book1, cart.getOrderItems().get(0).getBook());
                    Assertions.assertEquals(1, cart.getOrderItems().size());

                }, () -> {
                    cart.deleteBookFromCart(1);
                    Assertions.assertEquals(EXPECTED_TOTALPRICE_ZERO, cart.getTotalPrice());
                    Assertions.assertEquals(EXPECTED_COUNT_ZERO, cart.getOrderItems().size());

                }
        );
    }

    @Test
    void clearCart() {
        cart.addBookToCart(1L);
        cart.addBookToCart(1L);
        cart.addBookToCart(2L);

        cart.clearCart();

        Assertions.assertIterableEquals(Collections.emptyList(), cart.getOrderItems());
        Assertions.assertEquals(EXPECTED_TOTALPRICE_ZERO, cart.getTotalPrice());
    }

}