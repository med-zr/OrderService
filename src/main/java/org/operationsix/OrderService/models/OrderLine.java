package org.operationsix.OrderService.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "OrderLine")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderLine{
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @Id
    @Column(name = "product_id")
    private Long productID;

    @Column(nullable = false)
    private int quantity;

}


