package org.operationsix.OrderService.repository;

import org.operationsix.OrderService.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.userID = :userId")
    List<Order> findAllByUserId(Long userId);
}
