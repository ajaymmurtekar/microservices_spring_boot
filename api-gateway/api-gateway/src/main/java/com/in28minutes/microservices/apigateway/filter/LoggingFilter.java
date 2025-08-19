package com.in28minutes.microservices.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * This class serves purpose as Global filter which can be implemented for all the routes in API gateways
 */
@Component
public class LoggingFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
        logger.info("URL of request recieved is: {}", exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
