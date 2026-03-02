package com.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderStatus;
import com.bookstore.repository.BookRepo;
import com.bookstore.repository.CartRepo;
import com.bookstore.repository.OrderRepo;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final CartRepo cartRepo;
    private final BookRepo bookRepo;
    private final OrderRepo orderRepo;

    public OrderService(CartRepo c, BookRepo b, OrderRepo o) {
        cartRepo = c;
        bookRepo = b;
        orderRepo = o;
    }

    @Transactional // Place Order
    public Order placeOrder(Long userId) {

        List<Cart> items = cartRepo.findByUserId(userId);
        double total = 0;

        for (Cart c : items) {

            Book b = bookRepo.findById(c.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            total += b.getPrice() * c.getQuantity();

            b.setStock(b.getStock() - c.getQuantity());
            bookRepo.save(b);
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.PLACED);

        Order savedOrder = orderRepo.save(order);

        cartRepo.deleteByUserId(userId);

        return savedOrder;
    }

    @Transactional // Cancel Order
    public String cancelOrder(Long orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            return "Order already cancelled";
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);

        return "Order cancelled successfully";
    }

    // VIEW ALL ORDERS
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

}
