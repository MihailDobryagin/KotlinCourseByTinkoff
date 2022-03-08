package ru.tinkoff.fintech.homework.lesson3.animals

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CatTest {
    private val cat = spyk(Cat("Boris", "белый"))

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkEatMouse() {
        val weightDifference = 123

        val mouse = spyk<Mouse> {
            every { weight } returns weightDifference
        }

        val expectedWeight = cat.weight + weightDifference

        cat.eatMouse(mouse)

        assertAll(
            { assertFalse(mouse.alive) },
            { assertEquals(expectedWeight, cat.weight) },
            { assertEquals(1, cat.countOfEatenMouses) }
        )
    }

    @Test
    fun checkEatMouseWhenMouseIsDead() {
        val deadMouse = mockk<Mouse>()
        every { deadMouse.alive } returns false

        val expectedWeight = cat.weight

        cat.eatMouse(deadMouse)

        verify(exactly = 0) { cat.eat(any()) }
        assertEquals(expectedWeight, cat.weight)
    }
}