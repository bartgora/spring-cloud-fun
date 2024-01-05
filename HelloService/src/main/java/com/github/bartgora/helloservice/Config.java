package com.github.bartgora.helloservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class Config {

    final MetricsManager metricsManager;

    @Value("${PORT:8080}")
    String value;

    public Config(MetricsManager metricsManager) {
        this.metricsManager = metricsManager;
    }


    @Bean
    RouterFunction<ServerResponse> router() {
        return route()
                .nest(RequestPredicates.path("greetings"), builder -> {
                    builder.GET("/hello", h -> get());
                }).build();
    }

    public Mono<ServerResponse> get() {
        metricsManager.inc();
        return ServerResponse.ok().bodyValue("Hello From service! " + value);
    }
}
