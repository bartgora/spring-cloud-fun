package com.github.bartgora.helloservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class Config {

    final MetricsManager metricsManager;
    final WebClient webClient;

    @Value("${server.port}")
    String value;

    public Config(MetricsManager metricsManager, WebClient webClient) {
        this.metricsManager = metricsManager;
        this.webClient = webClient;
    }


    @Bean
    RouterFunction<ServerResponse> router() {
        return route()
                .nest(RequestPredicates.path("greetings"), builder -> builder.GET("/hello", h -> get())).build();
    }

    public Mono<ServerResponse> get() {
        return Mono.fromRunnable(() -> metricsManager.inc())
                .subscribeOn(Schedulers.boundedElastic())
                .then(retrieveData())
                .flatMap(name -> ServerResponse.ok().bodyValue("Hello From service! " + value + "  name:" + name)).log();
    }

    private Mono<String> retrieveData() {
        return webClient.get().uri("lb://name-service").retrieve().bodyToMono(String.class);
    }

}
