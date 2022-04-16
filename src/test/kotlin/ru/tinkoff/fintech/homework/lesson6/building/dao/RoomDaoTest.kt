package ru.tinkoff.fintech.homework.lesson6.building.dao

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room

abstract class RoomDaoTest {
    protected open lateinit var roomDao: RoomDao

    private val rooms = Array(3) { Room(it.toLong() + 1, "room$it", it) }

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
        Assertions.assertTrue(rooms.all { actualRooms[it.id] == it })
    }

    @Test
    fun checkAddRoom() {
        val insertingRoom = Room(null, "room4", 123)
        val id = roomDao.addRoom(Room(null, "room4", 123))
        val expectedRoom = insertingRoom.copy(id = id)


        val actualRooms = roomDao.getRooms()

        val actualRoom = actualRooms[id]

        Assertions.assertEquals(rooms.size.toLong() + 1, id)
        Assertions.assertEquals(expectedRoom, actualRoom)
    }

    @Test
    fun checkUpdateRoom() {
        val insertingRoom = Room(null, "room4", 123)
        val id = roomDao.addRoom(Room(null, "room4", 123))!!
        val room = insertingRoom.copy(id = id)
        val expectedRoom = room.copy(name = room.name + "-")

        roomDao.updateRoom(id, expectedRoom)

        val actualRoom = roomDao.getRoom(id)

        Assertions.assertEquals(expectedRoom, actualRoom)
    }

    protected abstract fun initRooms()

    protected abstract fun clearRooms()
}