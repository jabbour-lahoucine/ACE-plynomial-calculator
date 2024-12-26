package com.example.coefficientservice.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CoefficientResponse {
    private List<Double> coefficients;

    public CoefficientResponse() {
        coefficients = new ArrayList<>();
    }
    public List<Double> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Double> coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public String toString(){
        return "coefficients : " + coefficients.toString();
    }
}
