package ru.tinkoff.fintech.homework.lesson6.building

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {
    @Bean
    fun rooms() = mapOf<String, Int>()
}