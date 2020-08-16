package com.aleksandr0412.springdata.repositories;


import com.aleksandr0412.springdata.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Book findOneByTitle(String title); // ... where b.title = ?1
    Book findOneByTitleAndPublishYear(String title, int year); // ... where b.title = ?1 and b.publish_year = ?2
    List<Book> findAllByTitleStartingWith(String titleStartingWith);

    @Query("delete from Book b where b.publishYear < 2000")
    void deleteAllBooksOlder2000();

    void deleteAllByPublishYearLessThan(int year);
}
