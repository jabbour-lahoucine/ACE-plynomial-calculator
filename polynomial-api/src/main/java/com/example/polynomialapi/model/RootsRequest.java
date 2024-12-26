package com.example.polynomialapi.model;

import lombok.Data;

@Data
public class RootsRequest {
    private double[] coefficients;

    public double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

}
