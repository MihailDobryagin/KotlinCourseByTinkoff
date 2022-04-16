package ru.tinkoff.fintech.homework.lesson6.building.dao

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room

@ActiveProfiles("jdbc")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JdbcRoomDaoTest {
    @Autowired
    private lateinit var roomDao: RoomDao

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    private val rooms = Array(3) { Room(it.toLong() + 1, "room$it", it) }

    @BeforeAll
    fun beforeAll() {
        createDefaultRoomsTable()
    }

    @BeforeEach
    fun beforeEach() {
        initRooms()
    }

    @AfterEach
    fun afterEach() {
        clearRoomsTable()
    }

    @AfterAll
    fun afterAll() {
        dropRoomsTable()
    }


    @Test
    fun checkGetRooms() {
        val actualRooms = roomDao.getRooms()

        assertEquals(rooms.size, actualRooms.size)
        assertTrue(rooms.all { actualRooms[it.id] == it })
    }

    @Test
    fun checkAddRoom() {
        val insertingRoom = Room(null, "room4", 123)
        val id = roomDao.addRoom(Room(null, "room4", 123))
        val expectedRoom = insertingRoom.copy(id = id)


        val actualRooms = roomDao.getRooms()

        val actualRoom = actualRooms[id]

        assertEquals(rooms.size.toLong() + 1, id)
        assertEquals(expectedRoom, actualRoom)
    }

    @Test
    fun checkUpdateRoom() {
        val insertingRoom = Room(null, "room4", 123)
        val id = roomDao.addRoom(Room(null, "room4", 123))!!
        val room = insertingRoom.copy(id = id)
        val expectedRoom = room.copy(name = room.name + "-")

        roomDao.updateRoom(id, expectedRoom)

        val actualRoom = roomDao.getRoom(id)

        assertEquals(expectedRoom, actualRoom)
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

    private fun initRooms() {
        rooms.forEach {
            jdbcTemplate.update("insert into rooms(name, count_of_people) values (?, ?)", it.name, it.countOfPeople)
        }
    }

    private fun clearRoomsTable() {
        jdbcTemplate.update("truncate rooms")
        jdbcTemplate.update("ALTER SEQUENCE rooms_id_seq RESTART WITH 1")
    }

    private fun dropRoomsTable() {
        jdbcTemplate.update("DROP TABLE rooms")
    }
}