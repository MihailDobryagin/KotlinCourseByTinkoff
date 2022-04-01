package building

import com.google.gson.Gson
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ru.tinkoff.fintech.homework.lesson6.building.BuildingController
import ru.tinkoff.fintech.homework.lesson6.building.BuildingService
import ru.tinkoff.fintech.homework.lesson6.building.Room
import ru.tinkoff.fintech.homework.lesson6.building.request.dto.MoveWorkerDto
import ru.tinkoff.fintech.homework.lesson6.building.response.SuccessResponse

class BuildingControllerTest {
    private var buildingService = mockk<BuildingService>()

    private val buildingController = BuildingController(buildingService)

    private val mockMvc = MockMvcBuilders.standaloneSetup(buildingController).build()

    private val gson = Gson()

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkGetRoom() {
        val expectingRoom = Room(1, "room1", 123)
        every { buildingService.getRoom(1) } returns expectingRoom
        val requestBuilder = MockMvcRequestBuilders
            .get("/building/room")
            .param("roomId", "1")

        val response = mockMvc
            .perform(requestBuilder)
            .andExpect(status().isOk)
            .andReturn()
            .response
        val room = gson.fromJson(response.contentAsString, Room::class.java)

        assertEquals(expectingRoom, room)
    }

    @Test
    fun checkAddRoom() {
        every { buildingService.addRoom("name1") } returns 123
        val requestBuilder = MockMvcRequestBuilders
            .get("/building/rooms/add")
            .param("name", "name1")

        val response = mockMvc
            .perform(requestBuilder)
            .andExpect(status().isOk)
            .andReturn()
            .response

        val roomId = gson.fromJson(response.contentAsString, Long::class.java)

        verify { buildingService.addRoom("name1") }
        assertEquals(123, roomId)
    }

    @Test
    fun checkMovingWorkers() {
        every { buildingService.moveWorker(123, 456) } returns true
        val moveWorkerDto = MoveWorkerDto(123, 456)
        val requestBuilder = MockMvcRequestBuilders
            .post("/building/workers/move")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(gson.toJson(moveWorkerDto))

        val response = mockMvc
            .perform(requestBuilder)
            .andExpect(status().isOk)
            .andReturn()
            .response

        val result = gson.fromJson(response.contentAsString, SuccessResponse::class.java)

        verify { buildingService.moveWorker(123, 456) }
        assertTrue(result.success)
    }
}