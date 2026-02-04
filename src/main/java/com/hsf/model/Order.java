package com.hsf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    private String id;

    @Column(nullable = false)
    private String shippingAddressLine1;
    private String shippingAddressLine2;

    @Column(nullable = false)
    private String shippingCity;

    @Column(nullable = false)
    private String shippingCountry;

    @Column(nullable = false)
    private String shippingPostalCode;

    private String region;

    @CreatedDate
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name= "customer_id")
    private Customer customer;
}