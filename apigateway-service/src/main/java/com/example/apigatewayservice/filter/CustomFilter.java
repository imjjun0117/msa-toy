package com.example.apigatewayservice.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    private static final Logger log = LoggerFactory.getLogger(CustomFilter.class);

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.emptyList();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE Filter: request id -> {}", request.getId());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom POST Filter: response code -> {}", response.getStatusCode());
            }));
        };
    }

    public static class Config {
    }
}