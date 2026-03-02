package com.bookstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Order;
import com.bookstore.service.OrderService;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }
    
    // PLACE ORDER
    @PostMapping
    public Order place(@RequestParam Long userId) {
        return service.placeOrder(userId);
    }

    // VIEW ALL ORDERS
    @GetMapping
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    // CANCEL ORDER
    @PutMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        return service.cancelOrder(orderId);
    }
}
