package com.example.factorizationservice.controller;

import com.example.factorizationservice.model.FactorizationRequest;
import com.example.factorizationservice.model.FactorizationResponse;
import com.example.factorizationservice.service.FactorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/factorization")
public class FactorizationController {
    private final FactorizationService factorizationService;

    @Autowired
    public FactorizationController(FactorizationService factorizationService) {
        this.factorizationService = factorizationService;
    }
    @PostMapping("/calculate")
    public ResponseEntity<FactorizationResponse> getFactorization(@RequestBody FactorizationRequest request) {
        try {
            FactorizationResponse response = factorizationService.factorize(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
