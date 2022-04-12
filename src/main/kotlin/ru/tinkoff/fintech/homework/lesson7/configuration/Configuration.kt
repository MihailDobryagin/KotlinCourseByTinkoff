package ru.tinkoff.fintech.homework.lesson7.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.client.RestTemplate
import javax.sql.DataSource

@Configuration
class Configuration {
    @Bean
    fun restTemplate(@Value("\${rootUri}") rootUri: String): RestTemplate =
        RestTemplateBuilder().rootUri(rootUri).build()

    @Bean
    fun dataSource(): DataSource = TODO()

    @Bean
    fun jdbcTemplate(): JdbcTemplate = TODO()
}