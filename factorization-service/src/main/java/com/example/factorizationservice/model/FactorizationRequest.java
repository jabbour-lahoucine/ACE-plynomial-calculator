package com.example.factorizationservice.model;

import lombok.Data;
@Data
public class FactorizationRequest {
    private double[] coefficients;

    public double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }
}
