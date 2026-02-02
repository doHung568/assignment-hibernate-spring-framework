package com.hsf.repository;

import com.hsf.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    boolean existsByEmail(String email);

    @Query(value = "select o.order_id from orders o order by o.order_id DESC LIMIT 1", nativeQuery = true)
    String findLastOrderId();

    Order findOrderByOrderId(String orderId);
}
