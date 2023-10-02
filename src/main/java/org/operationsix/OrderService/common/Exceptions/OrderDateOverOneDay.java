package org.operationsix.OrderService.common.Exceptions;

public class OrderDateOverOneDay extends Exception{
    public OrderDateOverOneDay(String message){
        super(message);
    }
}
