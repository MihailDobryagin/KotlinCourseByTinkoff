package building

import com.google.gson.Gson
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ru.tinkoff.fintech.homework.lesson6.building.BuildingController
import ru.tinkoff.fintech.homework.lesson6.building.BuildingControllerHandler
import ru.tinkoff.fintech.homework.lesson6.building.response.SimpleResponse

class BuildingControllerHandlerTest {
    private val buildingController = BuildingController(mockk())
    private val mockMvc = MockMvcBuilders
        .standaloneSetup(buildingController)
        .setControllerAdvice(BuildingControllerHandler())
        .build()

    @Test
    fun checkExceptionHandling() {
        val requestBuilder = MockMvcRequestBuilders
            .get("/building/room/querty")

        val response = mockMvc
            .perform(requestBuilder)
            .andExpect(status().isOk)
            .andReturn()
            .response

        val error = Gson().fromJson(response.contentAsString, SimpleResponse::class.java)
        assertEquals(false, error.success)
    }
}