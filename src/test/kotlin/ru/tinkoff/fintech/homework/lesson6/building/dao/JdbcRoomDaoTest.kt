package ru.tinkoff.fintech.homework.lesson6.building.dao

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room

@ActiveProfiles("jdbc")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JdbcRoomDaoTest : RoomDaoTest() {
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    private val rooms = Array(3) { Room(it.toLong() + 1, "room$it", it) }

    @BeforeAll
    fun beforeAll() {
        createDefaultRoomsTable()
    }

    @AfterAll
    fun afterAll() {
        dropRoomsTable()
    }

    override fun initRooms() {
        rooms.forEach {
            jdbcTemplate.update("insert into rooms(name, count_of_people) values (?, ?)", it.name, it.countOfPeople)
        }
    }

    override fun clearRooms() {
        jdbcTemplate.update("truncate rooms")
        jdbcTemplate.update("ALTER SEQUENCE rooms_id_seq RESTART WITH 1")
    }

    private fun createDefaultRoomsTable() {
        jdbcTemplate.update(
            "CREATE TABLE rooms\n" +
                    "(\n" +
                    "    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),\n" +
                    "    name character varying COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    count_of_people integer NOT NULL,\n" +
                    "    CONSTRAINT rooms_pkey PRIMARY KEY (id)\n" +
                    ")"
        )
    }

    private fun dropRoomsTable() {
        jdbcTemplate.update("DROP TABLE rooms")
    }
}