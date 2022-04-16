package ru.tinkoff.fintech.homework.lesson6.building

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import ru.tinkoff.fintech.homework.lesson6.building.dao.RoomDao

//@ContextConfiguration("/application.properties")
@ActiveProfiles("dev")
@SpringBootTest
class JdbcTest {
    @Autowired
    private lateinit var roomDao: RoomDao

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun test() {
        val flag =true
        println(flag)
    }
}