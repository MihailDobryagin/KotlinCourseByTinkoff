package ru.tinkoff.fintech.homework.lesson6.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class Configuration {
    @Bean
    fun restTemplate(@Value("\${rootUri}") rootUri: String): RestTemplate =
        RestTemplateBuilder().rootUri(rootUri).build()
}