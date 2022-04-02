package workers

import com.google.gson.Gson
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ru.tinkoff.fintech.homework.lesson6.building.response.SimpleResponse
import ru.tinkoff.fintech.homework.lesson6.workers.WorkersController
import ru.tinkoff.fintech.homework.lesson6.workers.WorkersControllerHandler

class WorkersControllerHandlerTest {
    private val workersController = WorkersController(mockk())
    private val mockMvc = MockMvcBuilders
        .standaloneSetup(workersController)
        .setControllerAdvice(WorkersControllerHandler())
        .build()

    @Test
    fun checkExceptionHandling() {
        val requestBuilder = MockMvcRequestBuilders
            .get("/workers/qwerty")

        val response = mockMvc
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
            .response

        val error = Gson().fromJson(response.contentAsString, SimpleResponse::class.java)
        Assertions.assertEquals(false, error.success)
    }
}