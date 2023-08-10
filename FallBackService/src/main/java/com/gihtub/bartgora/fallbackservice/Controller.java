package com.gihtub.bartgora.fallbackservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class Controller {

    @GetMapping
    public String fallback() {
        return "FALLBACK";
    }
}
