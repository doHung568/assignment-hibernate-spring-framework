package com.hsf.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private String orderId;

    @Length(min = 2, max = 50, message = "Length of first name must between 2 and 50 characters")
    private String firstName;

    @Length(min = 2, max = 50, message = "Length of last name must between 2 and 50 characters")
    private String lastName;

    @Email(message = "Email is required")
    @NotBlank
    private String email;

    @NotBlank(message = "Phone is required")
    private String phoneNumber;

    @NotBlank(message = "Address Line 1 is required")
    private String shippingAddressLine1;
    private String shippingAddressLine2;

    @NotBlank(message = "City is required")
    private String shippingCity;

    @Column(nullable = false)
    private String shippingCountry;

    @Column(nullable = false)
    private String shippingPostalCode;

    @Length(min = 3, max = 10, message = "Postal code must be length between 3 and 10 digit")
    private String postalCode;
}
