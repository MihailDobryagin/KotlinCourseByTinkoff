package ru.tinkoff.fintech.homework.lesson6.building.dao

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import ru.tinkoff.fintech.homework.lesson6.building.repository.RoomsRepository

@ActiveProfiles("jpa")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JpaRoomDaoTest : RoomDaoTest() {
    @Autowired
    private lateinit var roomsRepository: RoomsRepository

    @BeforeAll
    fun beforeAll() {
        createDefaultRoomsTable()
    }

    @AfterAll
    fun afterAll() {
        dropRoomsTable()
    }

    override fun initRooms() {
        roomsRepository.saveAll(rooms.toMutableList())
    }

    override fun clearRooms() {
        roomsRepository.deleteAll()
    }
}