package com.in28minutes.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters( f -> f.addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("MyParam", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange")) //this is application name in eureka
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion")) //this is application name is eureka
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion")) //this is application name in eureka
                .build();
    }
}
