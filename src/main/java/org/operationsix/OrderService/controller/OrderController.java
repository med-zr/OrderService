package org.operationsix.OrderService.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.operationsix.OrderService.models.Order;
import org.operationsix.OrderService.models.Product;
import org.operationsix.OrderService.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "OrderController", description = "Controller responsible for managing orders")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {
    private final IOrderService orderService;
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllActiveOrders() {
        return orderService.getAllActiveOrders();
    }
    @GetMapping("/canceled")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllCanceledOrders() {
        return orderService.getAllCanceledOrders();
    }
    @GetMapping("/id/{orderID}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable Long orderID) {
        return orderService.getOrderById(orderID);
    }
    @GetMapping("/active/user/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getActiveOrdersByUser(@PathVariable Long userID) {
        return orderService.getActiveOrdersByUser(userID);
    }
    @GetMapping("/canceled/user/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getCanceledOrdersByUser(@PathVariable Long userID) {
        return orderService.getCanceledOrdersByUser(userID);
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestParam(name = "userID") Long userID, @RequestBody List<Product> products){
        return orderService.createOrder(userID,products);
    }
    @PutMapping("/cancel/{orderID}")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean cancelOrder(@PathVariable Long orderID) throws NoSuchFieldException{
        return orderService.cancelOrder(orderID);
    }
}
