package ru.tinkoff.fintech.homework.lesson6

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.Configuration
import ru.tinkoff.fintech.homework.lesson6.building.dao.DevRoomDao
import ru.tinkoff.fintech.homework.lesson6.building.dao.RoomDao
import javax.sql.DataSource

@TestConfiguration
class Configuration{
    @Bean
    @Profile("dev")
    fun roomDao(): RoomDao = DevRoomDao()
}
