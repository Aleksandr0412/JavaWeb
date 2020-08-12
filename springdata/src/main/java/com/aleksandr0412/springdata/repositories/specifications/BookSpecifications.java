package com.aleksandr0412.springdata.repositories.specifications;

import com.aleksandr0412.springdata.entities.Book;
import com.aleksandr0412.springdata.entities.Genre;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {
    public static Specification<Book> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Book> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Book> titleLike(String titlePart) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

    public static Specification<Book> publishYearGreaterOrEqualsThan(int minPublishYear) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("publishYear"), minPublishYear);
    }

    public static Specification<Book> publishYearLesserOrEqualsThan(int maxPublishYear) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("publishYear"), maxPublishYear);
    }

    public static Specification<Book> genreEquals(String genre) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("genre"), Genre.valueOf(genre));
    }
}