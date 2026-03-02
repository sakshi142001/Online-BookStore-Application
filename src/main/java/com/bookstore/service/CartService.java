package com.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.repository.BookRepo;
import com.bookstore.repository.CartRepo;

@Service
public class CartService {

    private final CartRepo cartRepo;
    private final BookRepo bookRepo;

    public CartService(CartRepo cartRepo, BookRepo bookRepo) {
        this.cartRepo = cartRepo;
        this.bookRepo = bookRepo;
    }

    public Cart addToCart(Long userId, Long bookId, int qty) {

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getStock() < qty)
            throw new RuntimeException("Out of stock");
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setBookId(bookId);
        cart.setQuantity(qty);

        return cartRepo.save(cart);
    }

    public List<Cart> getCartByUser(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    public void removeItem(Long cartId) {
        cartRepo.deleteById(cartId);
    }

}
