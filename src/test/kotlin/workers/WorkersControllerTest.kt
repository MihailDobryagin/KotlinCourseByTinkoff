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
import ru.tinkoff.fintech.homework.lesson6.workers.WorkersController
import ru.tinkoff.fintech.homework.lesson6.workers.WorkersService
import ru.tinkoff.fintech.homework.lesson6.workers.client.BuildingClient
import ru.tinkoff.fintech.homework.lesson6.workers.db.Worker
import ru.tinkoff.fintech.homework.lesson6.workers.db.WorkersDb

class WorkersControllerTest {
    private var buildingClient = mockk<BuildingClient>()
    private val workersDb = spyk<WorkersDb>()
    private var workersService = spyk(WorkersService(buildingClient, workersDb))
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
        clearAllMocks()
    }

    @Test
    fun checkGetById() {
        workersDb.addWorker(Worker(name = "qwerty"))
        val expectedWorker = Worker(1, "name1")
        workersDb.addWorker(expectedWorker)

        val requestBuilder = MockMvcRequestBuilders
            .get("/workers/1")

        val worker = sendReq<Worker>(requestBuilder)

        assertEquals(expectedWorker, worker)
    }

    @Test
    fun checkAddWorker() {
        val prevRequestBuilder = MockMvcRequestBuilders
            .post("/workers/add")
            .param("name", "")
        sendReq<Long>(prevRequestBuilder)
        sendReq<Long>(prevRequestBuilder)
        var requestBuilder = MockMvcRequestBuilders
            .post("/workers/add")
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
        for (i in 0 until 777)
            workersDb.addWorker(Worker(name = "qwerty"))
        workersDb.addWorker(Worker(name = "name1"))

        every { buildingClient.moveWorker(null, 123) } returns true
        val requestBuilder = MockMvcRequestBuilders
            .post("/workers/move/777")
            .param("to", "123")

        val movingResult = sendReq<Boolean>(requestBuilder)

        val worker = workersDb.getWorker(777)!!
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