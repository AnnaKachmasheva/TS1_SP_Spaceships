package cz.cvut.fel.ts1.sp.service;

import cz.cvut.fel.ts1.sp.model.Order;
import cz.cvut.fel.ts1.sp.model.Ship;
import cz.cvut.fel.ts1.sp.repository.OrderRepository;
import lombok.NoArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@NoArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        super();
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        boolean isUsed = false;
        for (Order o : getOrders()) {
            if (o.getShip().getId().equals(order.getShip().getId()) && o.getState()) {
                isUsed = true;
            }
        }
        if (!isUsed)
            return orderRepository.save(order);
        else
            throw new IllegalArgumentException();
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(Order oldOrder, Order newOrder) throws IllegalArgumentException {

        final Ship ship = newOrder.getShip();
        if (ship != null) {
            oldOrder.setShip(ship);
        } else {
            throw new IllegalArgumentException();
        }

        if (newOrder.getState() != null) {
            oldOrder.setState(newOrder.getState());
        }

        final Double price = newOrder.getPrice();
        if (price == null) {
            oldOrder.setPrice(0.0);
        } else if (price > 0) {
            oldOrder.setDistance(newOrder.getDistance());
            oldOrder.setPrice((oldOrder.getPrice() / oldOrder.getShip().getPrice())
                    * oldOrder.getShip().getPrice());
        } else {
            throw new IllegalArgumentException();
        }

        orderRepository.save(oldOrder);
        return oldOrder;
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public List<Order> getOrders() {
        var order = orderRepository.findAll();
        return order;
    }
}