package workers

import com.google.gson.Gson
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ru.tinkoff.fintech.homework.lesson6.workers.Worker
import ru.tinkoff.fintech.homework.lesson6.workers.WorkersController
import ru.tinkoff.fintech.homework.lesson6.workers.WorkersService
import ru.tinkoff.fintech.homework.lesson6.workers.client.BuildingClient

class WorkersControllerTest {
    private var buildingClient = mockk<BuildingClient>()
    private val workers = mutableMapOf<Long, Worker>()
    private var workersService = spyk(WorkersService(buildingClient, workers))
    private lateinit var workersController: WorkersController
    private lateinit var mockMvc: MockMvc
    private val gson = Gson()

    @BeforeEach
    fun beforeEach() {
        workersController = WorkersController(workersService)
        mockMvc = MockMvcBuilders.standaloneSetup(workersController).build()
    }

    @AfterEach
    fun afterEach() {
        workers.clear()
        clearAllMocks()
    }

    @Test
    fun checkGetById() {
        val expectedWorker = Worker(1, "name1")
        workers[1] = expectedWorker
        val requestBuilder = MockMvcRequestBuilders
            .get("/workers/1")

        val worker = sendReq<Worker>(requestBuilder)

        assertEquals(expectedWorker, worker)
    }

    @Test
    fun checkAddWorker() {
        val prevRequestBuilder = MockMvcRequestBuilders
            .get("/workers/add")
            .param("name", "")
        sendReq<Long>(prevRequestBuilder)
        sendReq<Long>(prevRequestBuilder)
        var requestBuilder = MockMvcRequestBuilders
            .get("/workers/add")
            .param("name", "expectedName")

        val workerId = sendReq<Long>(requestBuilder)

        requestBuilder = MockMvcRequestBuilders
            .get("/workers/2")
        val worker = sendReq<Worker>(requestBuilder)
        verify { workersService.addWorker("expectedName") }
        assertEquals(2, workerId)
        assertEquals("expectedName", worker.name)
    }

    @Test
    fun checkMoveWorker() {
        val worker = Worker(777, "name1", null)
        workers[worker.id] = worker
        every { buildingClient.moveWorker(null, 123) } returns true
        val requestBuilder = MockMvcRequestBuilders
            .get("/workers/move/777")
            .param("to", "123")

        val movingResult = sendReq<Boolean>(requestBuilder)
        verify { workersService.moveWorker(777, 123) }
        verify { buildingClient.moveWorker(null, 123) }
        assertTrue(movingResult)
        assertEquals(123, worker.roomId)
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