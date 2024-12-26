package com.example.rootsservice.controller;

import com.example.rootsservice.model.RootsRequest;
import com.example.rootsservice.model.RootsResponse;
import com.example.rootsservice.service.RootsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/roots")
public class RootsController {

    private final RootsService rootsService;

    @Autowired
    public RootsController(RootsService rootsService) {
        this.rootsService = rootsService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateRoots(@RequestBody RootsRequest request) {
        if (request == null || request.getCoefficients() == null || request.getCoefficients().length == 0) {
            return ResponseEntity.badRequest().body(new RootsResponse(List.of("Invalid polynomial coefficients")));
        }

        RootsResponse response = rootsService.calculateRoots(request.getCoefficients());
        return ResponseEntity.ok(response);
    }
}
