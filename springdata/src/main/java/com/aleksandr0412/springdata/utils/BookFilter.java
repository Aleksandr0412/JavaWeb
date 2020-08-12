package com.aleksandr0412.springdata.utils;

import com.aleksandr0412.springdata.entities.Book;
import com.aleksandr0412.springdata.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

@Getter
public class BookFilter {
    private Specification<Book> spec;
    private final String filterParameters;

    public BookFilter(MultiValueMap<String, String> params) {
        spec = Specification.where(null);
        StringBuilder parameters = new StringBuilder("&");
        if (params.containsKey("genre") && params.get("genre") != null) {
            for (String genre : params.get("genre")) {
                spec = spec.or(BookSpecifications.genreEquals(genre));
                parameters.append("genre=").append(genre).append('&');
            }
        }
        if (params.containsKey("minPrice") && !params.get("minPrice").get(0).equals("")) {
            String minPrice = params.get("minPrice").get(0);
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(minPrice)));
            parameters.append("minPrice=").append(minPrice).append('&');
        }
        if (params.containsKey("maxPrice") && !params.get("maxPrice").get(0).equals("")) {
            String maxPrice = params.get("maxPrice").get(0);
            spec = spec.and(BookSpecifications.priceLesserOrEqualsThan(Integer.parseInt(maxPrice)));
            parameters.append("maxPrice=").append(maxPrice).append('&');
        }
        if (params.containsKey("titlePart") && !params.get("titlePart").get(0).equals("")) {
            String titlePart = params.get("titlePart").get(0);
            spec = spec.and(BookSpecifications.titleLike(titlePart));
            parameters.append("titlePart=").append(titlePart).append('&');
        }
        if (params.containsKey("minPublishYear") && !params.get("minPublishYear").get(0).equals("")) {
            String minPublishYear = params.get("minPublishYear").get(0);
            spec = spec.and(BookSpecifications.publishYearGreaterOrEqualsThan(Integer.parseInt(minPublishYear)));
            parameters.append("minPublishYear=").append(minPublishYear).append('&');
        }
        if (params.containsKey("maxPublishYear") && !params.get("maxPublishYear").get(0).equals("")) {
            String maxPublishYear = params.get("maxPublishYear").get(0);
            spec = spec.and(BookSpecifications.publishYearLesserOrEqualsThan(Integer.parseInt(maxPublishYear)));
            parameters.append("maxPublishYear=").append(maxPublishYear).append('&');
        }
        if (parameters.toString().equals("&")) {
            this.filterParameters = "";
        } else filterParameters = parameters.toString();
    }
}