package com.github.bartgora.helloservice;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class Controller {
    @Value("${PORT:8080}")
    String value;


    private final Counter counter;

    public Controller(MeterRegistry myMeterRegistry) {
        counter = myMeterRegistry.counter("get_counter");
    }

    @GetMapping("/hello")
    public String get() {
        counter.increment();
        return "Hello From service! " + value;
    }
}
