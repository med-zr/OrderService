package org.operationsix.OrderService.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.operationsix.OrderService.common.OrderStatus;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "OrderTable")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long idOrder;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(name = "id_user",nullable = false)
    private Long userID;
    @Column(name = "creation_date",nullable = false)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonInclude
    private List<OrderLine> orderDetails = new ArrayList<>();
}
