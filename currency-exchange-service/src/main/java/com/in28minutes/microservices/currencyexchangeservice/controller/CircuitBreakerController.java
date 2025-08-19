package com.in28minutes.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@Retry(name = "sample-api", fallbackMethod = "handleErrorResponse")
//@CircuitBreaker(name = "default", fallbackMethod = "handleErrorResponse")
//@RateLimiter(name = "default")
@Bulkhead(name = "default")
public class CircuitBreakerController {
    Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    public String sampleApi(){
        logger.info("Some API called...");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return responseEntity.getBody();
    }

    public String handleErrorResponse(Exception ex) {
        logger.info("Inside handleErrorResponse...");
        return "Fallback Response";
    }
}
