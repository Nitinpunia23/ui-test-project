package com.example.bookmanagementapi.controller;

import com.example.bookmanagementapi.dto.BookResponse;
import com.example.bookmanagementapi.model.Book;
import com.example.bookmanagementapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return new ResponseEntity<Book>(bookService.addBook(book), HttpStatus.CREATED);  
    }

    @RequestMapping("/findByName")
    public ResponseEntity<Book> findBookByName(@RequestParam("name") String bookName){
        return new ResponseEntity<Book>(bookService.getBookByName(bookName), HttpStatus.OK);
    }

    @RequestMapping("/getAll")
    public ResponseEntity<BookResponse> getAllBooks(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) throws Exception {
        if(pageNum < 1) {
            throw new Exception("PageNum should be greater than 0");
           // return new ResponseEntity<ErrorRes>("PageNum should be greater than 0", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BookResponse>(bookService.getAllBooks(pageNum, pageSize), HttpStatus.OK);
    }

    @RequestMapping("/getAllBookSortedByAuthor")
    public ResponseEntity<BookResponse> getAllBooksSortedByAuthor(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) throws Exception {
        return new ResponseEntity<BookResponse>(bookService.getAllBooksSortedByAuthor(pageNum, pageSize), HttpStatus.OK);
    }

    @RequestMapping("/getAllBooks")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        return new ResponseEntity<Iterable<Book>>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book updatedBook){
       return bookService.updateBook(id, updatedBook);
    }

}
