package cz.cvut.fel.ts1.sp.service;

import cz.cvut.fel.ts1.sp.model.Order;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);

    Order getOrder(Long id);

    Order updateOrder(Order oidOrder, Order newOrder) throws IllegalArgumentException;

    void deleteOrder(Order order);

    List<Order> getOrders();
}