package com.example.coefficientservice.service;

import com.example.coefficientservice.model.CoefficientResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CoefficientService {
    public CoefficientResponse getCoefficients(String polynomialString) {
        CoefficientResponse response = new CoefficientResponse();
        Map<Integer, Double> coefficientsMap = parsePolynomial(polynomialString);

        if (coefficientsMap == null) {
            throw new IllegalArgumentException("Error: Invalid polynomial format");
        }

        // Convert Map to list, sorting keys in descending order (for coefficient display in highest to lowest power)
        List<Integer> sortedExponents = new ArrayList<>(coefficientsMap.keySet());
        Collections.sort(sortedExponents, Collections.reverseOrder());

        List<Double> coefficientsList = new ArrayList<>();
        for (int exponent : sortedExponents) {
            coefficientsList.add(coefficientsMap.get(exponent));
        }

        response.setCoefficients(coefficientsList);
        return response;
    }

    public static Map<Integer, Double> parsePolynomial(String polynomialString) {
        if (polynomialString == null || polynomialString.isEmpty()) {
            return null;
        }

        polynomialString = polynomialString.replaceAll("\\s", "");
        if (polynomialString.isEmpty())
            return null;

        Map<Integer, Double> coefficients = new HashMap<>();

        // Split the string into terms
        String[] terms = polynomialString.split("(?=[-+])");

        // First pass to find the highest degree
        int maxDegree = 0;
        for (String term : terms) {
            if (term.isEmpty()) continue;

            if (term.contains("x")) {
                int degree;
                if (term.contains("^")) {
                    try {
                        degree = Integer.parseInt(term.substring(term.indexOf('^') + 1));
                        maxDegree = Math.max(maxDegree, degree);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                } else {
                    maxDegree = Math.max(maxDegree, 1);
                }
            }
        }

        // Initialize all coefficients from 0 to maxDegree with 0.0
        for (int i = 0; i <= maxDegree; i++) {
            coefficients.put(i, 0.0);
        }

        // Process each term
        for (String term : terms) {
            if (term.isEmpty()) continue;

            // Handle pure numbers (constants)
            if (!term.contains("x")) {
                try {
                    double coefficient = Double.parseDouble(term);
                    coefficients.put(0, coefficients.get(0) + coefficient);
                    continue;
                } catch (NumberFormatException e) {
                    return null;
                }
            }

            // Handle terms with x
            double coefficient;
            int exponent;

            // Parse coefficient
            String coeffPart = term.substring(0, term.indexOf('x'));
            if (coeffPart.isEmpty() || coeffPart.equals("+")) {
                coefficient = 1.0;
            } else if (coeffPart.equals("-")) {
                coefficient = -1.0;
            } else {
                try {
                    coefficient = Double.parseDouble(coeffPart);
                } catch (NumberFormatException e) {
                    return null;
                }
            }

            // Parse exponent
            if (term.contains("^")) {
                try {
                    exponent = Integer.parseInt(term.substring(term.indexOf('^') + 1));
                } catch (NumberFormatException e) {
                    return null;
                }
            } else {
                exponent = 1;
            }

            coefficients.put(exponent, coefficients.get(exponent) + coefficient);
        }

        return coefficients;
    }
}
