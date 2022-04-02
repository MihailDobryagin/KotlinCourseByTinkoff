package building

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import ru.tinkoff.fintech.homework.lesson6.building.BuildingService
import ru.tinkoff.fintech.homework.lesson6.building.utils.RoomsDb

class BuildingServiceTest {
    private val roomsDb = spyk<RoomsDb>()
    private val buildingService = BuildingService(roomsDb)

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkMoveWorker() {
        roomsDb.addRoom("room0")
        roomsDb.addRoom("room1")
        val roomFrom = roomsDb.getRoom(0)!!
        roomFrom.countOfPeople = 1
        val roomTo = roomsDb.getRoom(1)!!

        buildingService.moveWorker(0, 1)

        assertAll(
            { assertEquals(0, roomFrom.countOfPeople) },
            { assertEquals(1, roomTo.countOfPeople) }
        )
    }
}