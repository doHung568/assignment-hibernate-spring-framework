package com.hsf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String addressLine1;
    private String addressLine2;
    private String postalCode;

    @ManyToOne
    @JoinColumn(name= "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name= "city_id")
    private City city;
}
