package cz.cvut.fel.ts1.sp.controller;

import cz.cvut.fel.ts1.sp.model.Order;
import cz.cvut.fel.ts1.sp.model.Ship;
import cz.cvut.fel.ts1.sp.service.OrderService;
import cz.cvut.fel.ts1.sp.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private OrderService orderService;
    private ShipService shipService;

    public OrderController() {}

    @Autowired
    public OrderController(OrderService orderService, ShipService shipService) {
        this.orderService = orderService;
        this.shipService = shipService;
    }

    @PostMapping(path = "/rest/orders")
    @ResponseBody
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (order == null || order.getDistance() == null || order.getDistance() < 1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Ship ship = order.getShip();
        if (ship == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Order order1 =  new Order();
        order1.setDistance(order.getDistance());
        order1.setPrice(order.getPrice());
        order1.setShip(order.getShip());
        order1.setState(true);

        final Order savedOrder = orderService.saveOrder(order1);

        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @GetMapping(path = "/rest/orders")
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping(path = "/rest/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable(value = "id") String pathId) {
        final Long id = shipService.convertIdToLong(pathId);
        if (id == null || id <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        final Order order = orderService.getOrder(id);
        if (order == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping(path = "/rest/orders/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable(value = "id") String pathId) {
        final ResponseEntity<Order> entity = getOrder(pathId);
        final Order savedOrder = entity.getBody();
        if (savedOrder == null)
            return entity;
        orderService.deleteOrder(savedOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/rest/orders/{id}")
    @ResponseBody
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") String pathId, @RequestBody Order order) {
        if (order.getDistance() < 1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        final ResponseEntity<Order> entity = getOrder(pathId);
        final Order savedOrder = entity.getBody();
        if (savedOrder == null)
            return entity;

        final Order result;
        try {
            double newPrice = order.getDistance() * savedOrder.getShip().getPrice();
            savedOrder.setPrice(newPrice);
            result = orderService.updateOrder(savedOrder, order);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}