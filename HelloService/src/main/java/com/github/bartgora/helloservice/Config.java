package com.github.bartgora.helloservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Config {

    @Value("${PORT:8080}")
    String value;

    @Bean
    RouterFunction<ServerResponse> router() {
        return route()
                .nest(RequestPredicates.path("greetings"), builder -> {
                    builder.GET("/hello", h -> get());
                }).build();
    }

    public Mono<ServerResponse> get() {
        return ServerResponse.ok().bodyValue("Hello From service! " + value);
    }
}
