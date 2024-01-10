package com.github.bartgora.nameservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NameServiceApplication

fun main(args: Array<String>) {
	runApplication<NameServiceApplication>(*args)
}
