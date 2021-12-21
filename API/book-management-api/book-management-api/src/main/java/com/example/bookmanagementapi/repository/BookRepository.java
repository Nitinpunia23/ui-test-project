package com.example.bookmanagementapi.repository;

import com.example.bookmanagementapi.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
     Book findByName(String name);

}
