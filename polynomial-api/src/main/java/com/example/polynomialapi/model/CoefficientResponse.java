package com.example.polynomialapi.model;

import lombok.Data;

@Data
public class CoefficientResponse {
    private double[] coefficients;

    public double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

}
