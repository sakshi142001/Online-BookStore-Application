package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookstore.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
