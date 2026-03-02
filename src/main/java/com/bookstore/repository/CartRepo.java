package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookstore.entity.Cart;
import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);

    void deleteByUserId(Long userId);

}
