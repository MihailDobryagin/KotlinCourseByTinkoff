package ru.tinkoff.fintech.homework.lesson6

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class Configuration {

    @Value("\${rootUri}")
    private lateinit var rootUri: String

    @Bean
    fun restTemplate(): RestTemplate = RestTemplateBuilder().rootUri(rootUri).build()
}