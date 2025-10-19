package com.example.shop.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public record SignupRequest(
        @Email String email,
        @NotBlank String password,
        @NotBlank String firstName,
        @NotBlank String lastName
) {}