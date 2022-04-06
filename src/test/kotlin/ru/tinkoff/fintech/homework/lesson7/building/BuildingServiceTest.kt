package ru.tinkoff.fintech.homework.lesson7.building

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import ru.tinkoff.fintech.homework.lesson7.building.db.Room
import ru.tinkoff.fintech.homework.lesson7.building.db.RoomsDb

class BuildingServiceTest {
    private val roomsDb = spyk<RoomsDb>()
    private val buildingService = BuildingService(roomsDb)

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkMoveWorker() {
        roomsDb.addRoom(Room(name = "room0", countOfPeople = 1))
        roomsDb.addRoom(Room(name = "room1"))

        buildingService.moveWorker(0, 1)

        val roomFrom = roomsDb.getRoom(0)!!
        val roomTo = roomsDb.getRoom(1)!!

        assertAll(
            { assertEquals(0, roomFrom.countOfPeople) },
            { assertEquals(1, roomTo.countOfPeople) }
        )
    }
}