package com.github.bartgora.helloservice;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    MeterRegistry myMeterRegistry(){
        return new SimpleMeterRegistry();
    }

}
