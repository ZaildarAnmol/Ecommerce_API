package com.example.shop.dto.auth;

public record JwtResponse(String accessToken, String tokenType) {
    public JwtResponse(String accessToken) { this(accessToken, "Bearer"); }
}
