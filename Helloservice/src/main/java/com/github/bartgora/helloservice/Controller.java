package com.github.bartgora.helloservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class Controller {
    @Value("${PORT:8080}")
    String value;

    @GetMapping("/hello")
    public String get() {
        return "Hello From service! " + value;
    }
}
