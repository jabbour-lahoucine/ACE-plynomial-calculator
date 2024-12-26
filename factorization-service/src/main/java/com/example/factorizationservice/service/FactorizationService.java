package com.example.factorizationservice.service;

import java.util.*;
import java.util.stream.Collectors;

import com.example.factorizationservice.model.FactorizationRequest;
import com.example.factorizationservice.model.FactorizationResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class FactorizationService {
    public FactorizationResponse factorize(FactorizationRequest request) {
        double[] coefficients = request.getCoefficients();

        if (coefficients == null || coefficients.length < 1) {
            FactorizationResponse response = new FactorizationResponse();
            response.setFactorization("Error: Invalid polynomial coefficients. Coefficients array cannot be null or empty.");
            return response;
        }


        List<Double> coefficientsList = Arrays.stream(coefficients)
                .boxed()
                .collect(Collectors.toList());
        Collections.reverse(coefficientsList); //reversed the array for the LaguerreSolver
        double[] reversedCoefficients = coefficientsList.stream().mapToDouble(Double::doubleValue).toArray();

        LaguerreSolver solver = new LaguerreSolver();
        Complex[] complexRoots = solver.solveAllComplex(reversedCoefficients, 0.0);

        if (complexRoots == null || complexRoots.length < 1) {
            return new FactorizationResponse("Error: Could not find any roots. Check coefficients or try a different algorithm.");
        }

        double[] roots = new double[complexRoots.length];

        for (int i = 0; i < complexRoots.length; i++) {
            roots[i] = complexRoots[i].getReal();
        }

        return buildFactorizationResponse(roots, coefficients);
    }

    private FactorizationResponse buildFactorizationResponse(double[] roots, double[] coefficients) {
        StringBuilder factorization = new StringBuilder();
        int n = coefficients.length - 1;

        if (n > 0) {
            if(coefficients[0] != 1){
                factorization.append(String.format("%.4f", coefficients[0])).append("*");
            }
            for (double root : roots) {
                factorization.append("(x - ").append(String.format("%.4f", root)).append(") * ");
            }

            factorization.delete(factorization.length() - 3, factorization.length()); //Remove last " * "
        }
        if (coefficients.length == 1) {
            factorization.append(String.format("%.4f", coefficients[0])); //Handles the constant polynomial
        }

        FactorizationResponse response = new FactorizationResponse();
        response.setFactorization(factorization.toString());
        return response;
    }
}




