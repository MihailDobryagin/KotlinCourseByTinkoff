package ru.tinkoff.fintech.homework.lesson6

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class Configuration {
    @Bean
    fun restTemplate(): RestTemplate = RestTemplateBuilder().rootUri("http://localhost:8080/").build()
}