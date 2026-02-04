package com.hsf.repository;

import com.hsf.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Query(value = "select o.id from orders o order by o.id DESC LIMIT 1", nativeQuery = true)
    String findLastId();

    Order findOrderById(String id);
}
