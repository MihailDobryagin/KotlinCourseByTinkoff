package ru.tinkoff.fintech.homework.lesson6

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
class Application

fun main() {
    runApplication<Application>()
}