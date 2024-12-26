package com.example.polynomialapi.service;

import com.example.polynomialapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class PolynomialService {

    private final WebClient webClient;

    @Autowired
    public PolynomialService(@LoadBalanced WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<PolynomialResponse> calculatePolynomial(String polynomial) {
        PolynomialResponse response = new PolynomialResponse();

        // 1. Get Coefficients from the Coefficient Service
        CoefficientRequest coefficientRequest = new CoefficientRequest();
        coefficientRequest.setPolynomial(polynomial);

        return webClient.post()
                .uri("http://coefficient-service:8081/api/coefficients/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(coefficientRequest)
                .retrieve()
                .bodyToMono(CoefficientResponse.class)
                .flatMap(coefficientResponse -> {
                    if (coefficientResponse == null || coefficientResponse.getCoefficients() == null) {
                        response.setRoots(Collections.singletonList("Error calculating coefficients."));
                        response.setFactorization("Error calculating coefficients");
                        return Mono.just(response);
                    }

                    // Convert List<Double> to double[]
                    double[] coefficients = coefficientResponse.getCoefficients();

                    // 2. Get Roots from Root Service
                    RootsRequest rootsRequest = new RootsRequest();
                    rootsRequest.setCoefficients(coefficients);

                    return webClient.post()
                            .uri("http://roots-service:8082/api/roots/calculate")
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(rootsRequest)
                            .retrieve()
                            .bodyToMono(RootsResponse.class)
                            .flatMap(rootsResponse -> {
                                if (rootsResponse == null || rootsResponse.getRoots() == null) {
                                    response.setRoots(Collections.singletonList("Error calculating roots."));
                                    response.setFactorization("Error calculating roots");
                                    return Mono.just(response);
                                }

                                response.setRoots(rootsResponse.getRoots());

                                // 3. Get Factorization from Factorization Service (Optional)
                                FactorizationRequest factorizationRequest = new FactorizationRequest();
                                factorizationRequest.setCoefficients(coefficients);

                                return webClient.post()
                                        .uri("http://factorization-service:8083/api/factorization/calculate")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(factorizationRequest)
                                        .retrieve()
                                        .bodyToMono(FactorizationResponse.class)
                                        .map(factorizationResponse -> {
                                            if (factorizationResponse != null) {
                                                response.setFactorization(factorizationResponse.getFactorization());
                                            }
                                            return response;
                                        })
                                        .defaultIfEmpty(response);
                            });
                })
                .onErrorResume(e -> {
                    response.setRoots(Collections.singletonList("Error: An error occurred during processing."));
                    response.setFactorization(e.getMessage());
                    return Mono.just(response);
                });
    }
}