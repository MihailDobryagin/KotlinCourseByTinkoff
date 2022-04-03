package building

import com.google.gson.Gson
import com.ninjasquad.springmockk.MockkBean
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.tinkoff.fintech.homework.lesson6.Application
import ru.tinkoff.fintech.homework.lesson6.building.BuildingService
import ru.tinkoff.fintech.homework.lesson6.building.db.Room
import ru.tinkoff.fintech.homework.lesson6.building.dto.request.MoveWorkerDto

@SpringBootTest(classes = [Application::class])
@AutoConfigureMockMvc
class BuildingControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var buildingService: BuildingService

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
            .get("/building/room/1")

        val room = sendReq<Room>(requestBuilder)

        assertEquals(expectingRoom, room)
    }

    @Test
    fun checkAddRoom() {
        every { buildingService.addRoom("name1") } returns 123
        val requestBuilder = MockMvcRequestBuilders
            .post("/building/rooms/add")
            .param("name", "name1")

        val roomId = sendReq<Long>(requestBuilder)

        verify { buildingService.addRoom("name1") }
        assertEquals(123, roomId)
    }

    @Test
    fun checkMovingWorkers() {
        every { buildingService.moveWorker(123, 456) } returns Unit
        val moveWorkerDto = MoveWorkerDto(123, 456)
        val requestBuilder = MockMvcRequestBuilders
            .post("/building/workers/move")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(gson.toJson(moveWorkerDto))

        sendReq<Any>(requestBuilder)

        verify { buildingService.moveWorker(123, 456) }
    }

    private inline fun <reified T> sendReq(requestBuilder: RequestBuilder): T {
        val response = mockMvc
            .perform(requestBuilder)
            .andExpect(status().isOk)
            .andReturn()
            .response

        return gson.fromJson(response.contentAsString, T::class.java)
    }
}