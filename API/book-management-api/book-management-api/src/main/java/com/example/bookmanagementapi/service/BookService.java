package com.example.bookmanagementapi.service;

import com.example.bookmanagementapi.dto.BookResponse;
import com.example.bookmanagementapi.model.Book;
import com.example.bookmanagementapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public BookResponse getAllBooks(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        final Page<Book> books = bookRepository.findAll(pageable);
        long totalBooks = books.getTotalElements();
        int totalPages = books.getTotalPages();

        // create book response
        BookResponse bookResponse =  new BookResponse();
        bookResponse.setPageNum(pageNum);
        bookResponse.setPageSize(pageSize);
        bookResponse.setTotalBooks(totalBooks);
        bookResponse.setTotalPages(totalPages);
        bookResponse.setBooks(books.getContent());

        return bookResponse;
    }

    public BookResponse getAllBooksSortedByAuthor(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize, Sort.by("author"));
        final Page<Book> books = bookRepository.findAll(pageable);
        long totalBooks = books.getTotalElements();
        int totalPages = books.getTotalPages();

        // create book response
        BookResponse bookResponse =  new BookResponse();
        bookResponse.setPageNum(pageNum);
        bookResponse.setPageSize(pageSize);
        bookResponse.setTotalBooks(totalBooks);
        bookResponse.setTotalPages(totalPages);
        bookResponse.setBooks(books.getContent());

        return bookResponse;
    }

    public Iterable<Book> getAllBooks() {
        final Iterable<Book> books = bookRepository.findAll();
        return books;
    }

    public Book getBookByName(String bookName){
        return bookRepository.findByName(bookName);
    }
    public Book addBook(Book book) {
        final Book savedBook = bookRepository.save(book);
        return  savedBook;
    }

    public Book updateBook(Long id, Book book){
        Book existingBook = bookRepository.findById(id).get();
        if(existingBook!=null) {
            existingBook.setAuthor(book.getAuthor());
            existingBook.setName(book.getName());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setPrice(book.getPrice());

            // save it
            bookRepository.save(existingBook);
            return existingBook;
        } else{
            return null;
        }
    }
}
