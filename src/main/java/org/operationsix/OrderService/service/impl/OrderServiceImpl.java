package org.operationsix.OrderService.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.operationsix.OrderService.common.Exceptions.OrderDateOverOneDay;
import org.operationsix.OrderService.common.OrderStatus;
import org.operationsix.OrderService.models.Order;
import org.operationsix.OrderService.models.OrderLine;
import org.operationsix.OrderService.models.Product;
import org.operationsix.OrderService.repository.IOrderRepo;
import org.operationsix.OrderService.service.IOrderService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class OrderServiceImpl implements IOrderService{

    private final IOrderRepo orderRepo;

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> getAllActiveOrders() {
        return orderRepo.findAll().stream()
                .filter(order -> (order.getOrderStatus().equals(OrderStatus.ACTIVE)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllCanceledOrders() {
        return orderRepo.findAll().stream()
                .filter(order -> (order.getOrderStatus().equals(OrderStatus.CANCELED)))
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrderById(Long idOrder) {
        if(orderRepo.findById(idOrder).isPresent()) return orderRepo.findById(idOrder).get();
        else{
            log.info("There is no order with that Id");
            return null;
        }
    }
    @Override
    public List<Order> getCanceledOrdersByUser(Long idUser) {
        return orderRepo.findAllByUserId(idUser).stream()
                .filter(order -> (order.getOrderStatus().equals(OrderStatus.CANCELED)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getActiveOrdersByUser(Long idUser) {
        return orderRepo.findAllByUserId(idUser).stream()
                .filter(order -> (order.getOrderStatus().equals(OrderStatus.ACTIVE)))
                .collect(Collectors.toList());
    }

    @Override
    public double getOrderTotal(Long orderId) {
        return 0;
    }
    @Override
    public Order createOrder(Long userID, List<Product> products) {
        Order order = new Order();
        order.setUserID(userID);
        order.setOrderStatus(OrderStatus.ACTIVE);
        LocalDateTime date = LocalDateTime.now();
        order.setCreationDate(date);
        for(Product product : products){
            OrderLine orderLine = new OrderLine();
            orderLine.setProductID(product.getIdProduct());
            orderLine.setQuantity(product.getQuantity());
            orderLine.setOrder(order);
            order.getOrderDetails().add(orderLine);
        }
        orderRepo.save(order);
        return order;
    }

    @Override
    public Boolean cancelOrder(Long orderID) throws NoSuchFieldException{
        if(orderRepo.findById(orderID).isPresent()) {
            try{
                Order order = orderRepo.findById(orderID).get();
                long duration = Duration.between(order.getCreationDate(),LocalDateTime.now()).toHours();
                if(duration < 24) {
                    order.setOrderStatus(OrderStatus.CANCELED);
                    orderRepo.save(order);
                    return true;
                }else{
                    throw new OrderDateOverOneDay("Can't cancel order that have a creation date passed 24 hours");
                }
            }catch(OrderDateOverOneDay e){
                System.out.println(e.getMessage());
                return false;
            }

        }
        else {
            throw new NoSuchFieldException();
        }
    }
}



