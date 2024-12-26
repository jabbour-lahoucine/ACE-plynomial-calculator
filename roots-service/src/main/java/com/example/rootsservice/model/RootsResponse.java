package com.example.rootsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class RootsResponse {
    private List<String> roots;

    public RootsResponse() {
        // Default constructor
    }

    public RootsResponse(List<String> roots) {
        this.roots = roots;
    }

    public void setRoots(List<String> roots) {
        this.roots = roots;
    }

    public List<String> getRoots() {
        return roots;
    }
}
