package ru.tinkoff.fintech.homework.lesson6.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
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
    @ConfigurationProperties(prefix = "datasource")
    fun hikariConfig(): HikariConfig{
        return HikariConfig()
    }

    @Profile("jdbc")
    @Bean
    fun dataSource(
        hikariConfig: HikariConfig
    ): DataSource {
        return HikariDataSource(hikariConfig)
    }

    @Profile("jdbc")
    @Bean
    fun jdbcTemplate(dataSource: DataSource): JdbcTemplate = JdbcTemplate(dataSource)
}