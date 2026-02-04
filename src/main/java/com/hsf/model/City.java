package com.hsf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "cities")
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String city;

    private String region;

    @ManyToOne
    @JoinColumn(name= "country_id")
    private Country country;

    @OneToMany(mappedBy = "city")
    private Set<Address> addresses;
}
