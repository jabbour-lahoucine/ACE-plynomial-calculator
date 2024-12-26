package com.example.polynomialapi.model;

import lombok.Data;

import java.util.List;

@Data
public class RootsResponse {
    private List<String> roots;
    public List<String> getRoots() {
        return roots;
    }

    public void setRoots(List<String> roots) {
        this.roots = roots;
    }

}
