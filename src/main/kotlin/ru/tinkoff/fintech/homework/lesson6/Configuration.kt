package ru.tinkoff.fintech.homework.lesson6

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.tinkoff.fintech.homework.lesson6.workers.db.Worker

@Configuration
class Configuration {
    @Bean
    fun workers() = mutableMapOf<Long, Worker>()

    @Bean
    fun globalURI() = "http://localhost:8080/"
}