package ru.tinkoff.fintech.homework.lesson6.building.dao

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room

abstract class RoomDaoTest {
    @Autowired
    protected lateinit var roomDao: RoomDao

    protected val rooms = Array(3) { Room(null, "room" + ('a' + it), it) }

    @BeforeEach
    fun beforeEach() {
        initRooms()
    }

    @AfterEach
    fun afterEach() {
        clearRooms()
    }

    @Test
    fun checkGetRooms() {
        val actualRooms = roomDao.getRooms()

        Assertions.assertEquals(rooms.size, actualRooms.size)
        Assertions.assertTrue(actualRooms.all {
            rooms.any { room ->
                room.name == it.name
                        && room.countOfPeople == it.countOfPeople
            }
                    && it.id != null
        })
    }

    @Test
    fun checkAddRoom() {
        val insertingRoom = Room(null, "room4", 123)
        val id = roomDao.addRoom(Room(null, "room4", 123))
        val expectedRoom = insertingRoom.copy(id = id)

        val actualRoom = roomDao.getRoom(id!!)

        Assertions.assertEquals(expectedRoom, actualRoom)
    }

    @Test
    fun checkUpdateRoom() {
        val insertingRoom = Room(null, "room4", 123)
        val id = roomDao.addRoom(Room(null, "room4", 123))!!
        val room = insertingRoom.copy(id = id)
        val expectedRoom = room.copy(name = room.name + "-")

        roomDao.updateRoom(expectedRoom)

        val actualRoom = roomDao.getRoom(id)

        Assertions.assertEquals(expectedRoom, actualRoom)
    }

    protected abstract fun initRooms()

    protected abstract fun clearRooms()
}