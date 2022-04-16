package ru.tinkoff.fintech.homework.lesson6

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("jdbc")
@SpringBootTest
class JdbcTemplateTest(
    @Value("\${datasource.testNumber}") expectedTestNumber: Int,
) {
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    private val expectedTestNumber = 123987

    @Test
    fun checkByExistingTable() {
        val row = jdbcTemplate.queryForRowSet("select * from test")
        row.next()
        val actualTestNumber = row.getInt("test_number")
        assertEquals(expectedTestNumber, actualTestNumber)
    }
}