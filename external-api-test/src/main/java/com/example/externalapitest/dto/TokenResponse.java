package com.example.externalapitest.dto;

public class TokenResponse {
    private String token;

    private TokenResponse() {

    }

    public TokenResponse(String token) {
        this.token = token;
    }

    public static TokenResponse from(String token) {
        return new TokenResponse(token);
    }

    public String getToken() {
        return token;
    }
}
