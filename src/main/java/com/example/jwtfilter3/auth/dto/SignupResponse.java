package com.example.jwtfilter3.auth.dto;

import lombok.Getter;

@Getter
public class SignupResponse {
    private final String bearerJwt;
    private final Long id;

    public SignupResponse(String bearerJwt, Long id) {
        this.bearerJwt = bearerJwt;
        this.id = id;
    }
}
