package ru.tinkoff.fintech.homework.lesson6.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.*
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.client.RestTemplate
import javax.sql.DataSource

@Configuration
class Configuration {
    @Bean
    fun restTemplate(@Value("\${rootUri}") rootUri: String): RestTemplate =
        RestTemplateBuilder().rootUri(rootUri).build()

    @Profile("jdbc")
    @Bean
    fun dataSource(
        @Value("\${datasource.driverClassName}") driverClassName: String,
        @Value("\${datasource.jdbcUrl}") jdbcUrl: String,
        @Value("\${datasourceClassName}") className: String,
        @Value("\${datasource.username}") username: String,
        @Value("\${datasource.password}") password: String,
    ): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = jdbcUrl
        config.driverClassName = driverClassName
        config.username = username
        config.password = password

        return HikariDataSource(config)
    }

    @Profile("jdbc")
    @Bean
    fun jdbcTemplate(dataSource: DataSource): JdbcTemplate = JdbcTemplate(dataSource)
}