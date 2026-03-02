package com.bookstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    // Constructor Injection
    public BookController(BookService service) {
        this.service = service;
    }

    // GET ALL BOOKS
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return service.getAllBooks();
    }
    // ADD BOOK
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.save(book);
    }
}
