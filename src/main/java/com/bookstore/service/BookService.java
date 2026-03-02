package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepo;

@Service
public class BookService {

    @Autowired
    private BookRepo repo;

    // Convert Entity → DTO
    public BookDTO convertToDTO(Book book) {

        BookDTO dto = new BookDTO();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());

        return dto;
    }

    // Get all books
    public List<BookDTO> getAllBooks() {

        List<Book> books = repo.findAll();

        return books.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Book save(Book book) {
        return repo.save(book);
    }

}
