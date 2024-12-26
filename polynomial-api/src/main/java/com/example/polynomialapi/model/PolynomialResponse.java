package com.example.polynomialapi.model;

import lombok.Data;
import java.util.List;

@Data
public class PolynomialResponse {
    private List<String> roots;
    private String factorization;

    public List<String> getRoots() {
        return roots;
    }

    public void setRoots(List<String> roots) {
        this.roots = roots;
    }

    public String getFactorization() {
        return factorization;
    }

    public void setFactorization(String factorization) {
        this.factorization = factorization;
    }

}
