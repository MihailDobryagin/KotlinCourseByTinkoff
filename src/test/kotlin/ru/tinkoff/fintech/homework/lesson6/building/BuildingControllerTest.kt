package ru.tinkoff.fintech.homework.lesson6.building

import com.google.gson.Gson
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room
import ru.tinkoff.fintech.homework.lesson6.building.dto.request.MoveWorkerDto

class BuildingControllerTest {

    private lateinit var buildingController: BuildingController
    private lateinit var mockMvc: MockMvc

    private var buildingService: BuildingService = mockk()

    private val gson = Gson()

    @BeforeEach
    fun beforeEach() {
        buildingController = BuildingController(buildingService)
        mockMvc = MockMvcBuilders.standaloneSetup(buildingController).build()
    }

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