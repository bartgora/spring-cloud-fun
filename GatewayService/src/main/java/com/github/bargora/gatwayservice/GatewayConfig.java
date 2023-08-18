package com.github.bargora.gatwayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route("greetings", r -> r.path("/greetings/*")
                        .filters(f -> f.circuitBreaker(c -> c.setName("circut").setFallbackUri("forward:/fallback")))
                        .uri("lb://hello-service"))
                .route("fallback", r -> r.path("/fallback").uri("lb://fallback-service"))
                .build();
    }


}
