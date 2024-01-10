package com.github.bartgora.nameservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/name")
class NameController {
    @Value("\${given.name}")
    var value: String? = null

    @GetMapping
    fun getName(): String {
        return "$value"
    }

}