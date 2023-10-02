package org.operationsix.OrderService.models;



public class Product {
    private Long idProduct;
    private int quantity;
    Product(){

    }
    Product(Long idProduct, int quantity){
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
