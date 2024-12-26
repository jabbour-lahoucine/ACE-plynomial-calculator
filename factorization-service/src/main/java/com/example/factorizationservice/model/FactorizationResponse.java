package com.example.factorizationservice.model;

import lombok.Data;

@Data
public class FactorizationResponse {
    private String factorization;

    public FactorizationResponse(String s) {
        this.factorization = s;
    }

    public FactorizationResponse() {

    }

    public String getFactorization() {
        return factorization;
    }

    public void setFactorization(String factorization) {
        this.factorization = factorization;
    }
}
