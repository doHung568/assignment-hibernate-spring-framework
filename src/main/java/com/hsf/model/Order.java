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
    private String orderId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String addressLine1;

    private String addressLine2;

    @Column(nullable = false)
    private String city;
    private String region;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String country;

    @CreatedDate
    private LocalDateTime orderDate;
}