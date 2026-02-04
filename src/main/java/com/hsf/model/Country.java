package com.hsf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String countryCode;

    @Column(nullable = false, unique = true)
    private String countryName;

    @OneToMany(mappedBy = "country")
    private Set<City> cities;
}
