package com.bookstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Cart;
import com.bookstore.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }
    
    // ADD To CART
    @PostMapping
    public Cart add(
            @RequestParam Long userId,
            @RequestParam Long bookId,
            @RequestParam int qty) {
        return service.addToCart(userId, bookId, qty);
    }
    
    // VIEW CART
    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable Long userId) {
        return service.getCartByUser(userId);
    }
    
    // REMOVE ITEM FROM CART
    @DeleteMapping("/item/{cartId}")
    public String removeItem(@PathVariable Long cartId) {
        service.removeItem(cartId);
        return "Item removed from cart";
    }

}
