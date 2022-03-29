package ru.tinkoff.fintech.homework.lesson6

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.tinkoff.fintech.homework.lesson6.building.Room
import ru.tinkoff.fintech.homework.lesson6.workers.Worker

@Configuration
class Configuration {
    @Bean
    fun workers() = mutableMapOf<Long, Worker>()

    @Bean
    fun rooms() = mutableMapOf<Long, Room>()

    @Bean
    fun globalURI() = "http://localhost:8080/"
}