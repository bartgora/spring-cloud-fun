package com.github.bartgora.helloservice;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MetricsManager {
    private final Counter counter;

    public MetricsManager(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("get_counter_functional");
    }

    public void inc(){
        counter.increment();
    }
}
