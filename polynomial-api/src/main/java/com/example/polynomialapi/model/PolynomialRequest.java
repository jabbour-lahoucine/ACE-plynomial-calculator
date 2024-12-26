package com.example.polynomialapi.model;

import lombok.Data;

@Data
public class PolynomialRequest {
    private String polynomial;

    public String getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(String polynomial) {
        this.polynomial = polynomial;
    }

}
