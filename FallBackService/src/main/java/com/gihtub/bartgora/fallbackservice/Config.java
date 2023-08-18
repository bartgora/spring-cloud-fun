package com.gihtub.bartgora.fallbackservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Config {

    @Bean
    RouterFunction<ServerResponse> router() {
        return route().
                GET("/fallback", r -> get()
                ).build();
    }

    public Mono<ServerResponse> get() {
        return ServerResponse.ok().bodyValue("FALLBACK");
    }
}
