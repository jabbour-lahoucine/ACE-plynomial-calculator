package com.example.polynomialapi.controller;

import com.example.polynomialapi.model.PolynomialRequest;
import com.example.polynomialapi.model.PolynomialResponse;
import com.example.polynomialapi.service.PolynomialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/polynomial")
@CrossOrigin(origins = "http://localhost:3004")
public class PolynomialController {

    private final PolynomialService polynomialService;

    @Autowired
    public PolynomialController(PolynomialService polynomialService) {
        this.polynomialService = polynomialService;
    }

    @PostMapping("/calculate")
    public Mono<ResponseEntity<PolynomialResponse>> calculatePolynomial(@RequestBody PolynomialRequest request) {
        return polynomialService.calculatePolynomial(request.getPolynomial())
                .map(ResponseEntity::ok);
    }
}
