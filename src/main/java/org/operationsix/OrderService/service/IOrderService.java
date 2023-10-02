package org.operationsix.OrderService.service;

import org.operationsix.OrderService.models.Order;
import org.operationsix.OrderService.models.Product;

import java.util.List;

public interface IOrderService {
    public List<Order> getAllOrders();
    public List<Order> getAllActiveOrders();
    public List<Order> getAllCanceledOrders();
    public Order getOrderById(Long idOrder);
    public List<Order> getCanceledOrdersByUser(Long idUser);
    public List<Order> getActiveOrdersByUser(Long idUser);
    public double getOrderTotal(Long orderID);
    public Order createOrder(Long userID, List<Product> Products);
    public Boolean cancelOrder(Long orderID) throws NoSuchFieldException;
}
