package ru.tinkoff.fintech.homework.lesson7.workers

import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

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

        mockMvc
            .perform(requestBuilder)
            .andExpect(status().isInternalServerError)
    }
}