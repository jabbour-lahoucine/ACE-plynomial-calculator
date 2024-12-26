package com.example.rootsservice.model;

import lombok.Data;

@Data
public class RootsRequest {
    private double[] coefficients;

    public RootsRequest(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public RootsRequest() {

    }



    public double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }
}
