package building

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import ru.tinkoff.fintech.homework.lesson6.building.BuildingService
import ru.tinkoff.fintech.homework.lesson6.building.Room

class BuildingServiceTest {
    private val rooms: MutableMap<Long, Room> = mutableMapOf()

    private val buildingService = BuildingService(rooms)

    @BeforeEach
    fun beforeAll() {
        rooms.clear()
    }

    @Test
    fun checkMoveWorker() {
        val roomFrom = Room(0, "room0", 1)
        val roomTo = Room(1, "room1", 0)
        rooms[0] = roomFrom
        rooms[1] = roomTo

        buildingService.moveWorker(0, 1)

        assertAll(
            { assertEquals(0, roomFrom.countOfPeople) },
            { assertEquals(1, roomTo.countOfPeople) }
        )
    }
}