package com.example.coefficientservice.model;

import lombok.Data;
@Data
public class CoefficientRequest {
    private String polynomial;

    public String getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(String polynomial) {
        this.polynomial = polynomial;
    }

}
