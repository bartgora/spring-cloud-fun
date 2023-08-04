package com.github.bartgora.helloservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "greetings")
public class Controller {
    @Value("${custom.value}")
    String value;

    @GetMapping("/hello")
    public String get() {
        return "Hello From service! " + value;
    }
}
