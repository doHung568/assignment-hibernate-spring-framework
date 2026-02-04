package com.hsf.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public interface CustomerService {
    boolean checkExistByEmail(String email);

    boolean checkExistByPhoneNumber(String phoneNumber);
}
