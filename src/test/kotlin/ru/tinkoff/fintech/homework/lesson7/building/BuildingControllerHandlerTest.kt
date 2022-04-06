package ru.tinkoff.fintech.homework.lesson7.building

import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

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

        mockMvc
            .perform(requestBuilder)
            .andExpect(status().isInternalServerError)
    }
}