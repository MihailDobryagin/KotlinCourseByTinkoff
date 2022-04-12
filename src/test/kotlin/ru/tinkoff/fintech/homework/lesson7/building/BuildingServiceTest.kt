package ru.tinkoff.fintech.homework.lesson7.building

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import ru.tinkoff.fintech.homework.lesson7.building.dao.DevRoomDao
import ru.tinkoff.fintech.homework.lesson7.building.entities.Room

class BuildingServiceTest {
    private val devRoomDao = spyk<DevRoomDao>()
    private val buildingService = BuildingService(devRoomDao)

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkMoveWorker() {
        devRoomDao.addRoom(Room(name = "room0", countOfPeople = 1))
        devRoomDao.addRoom(Room(name = "room1"))

        buildingService.moveWorker(0, 1)

        val roomFrom = devRoomDao.getRoom(0)!!
        val roomTo = devRoomDao.getRoom(1)!!

        assertAll(
            { assertEquals(0, roomFrom.countOfPeople) },
            { assertEquals(1, roomTo.countOfPeople) }
        )
    }
}