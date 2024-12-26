package com.example.coefficientservice.controller;

import com.example.coefficientservice.model.CoefficientRequest;
import com.example.coefficientservice.model.CoefficientResponse;
import com.example.coefficientservice.service.CoefficientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coefficients")
public class CoefficientController {

    private final CoefficientService coefficientService;

    @Autowired
    public CoefficientController(CoefficientService coefficientService) {
        this.coefficientService = coefficientService;
    }
    @PostMapping("/calculate")
    public ResponseEntity<CoefficientResponse> getCoefficients(@RequestBody CoefficientRequest request) {
        try {
            CoefficientResponse response = coefficientService.getCoefficients(request.getPolynomial());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
