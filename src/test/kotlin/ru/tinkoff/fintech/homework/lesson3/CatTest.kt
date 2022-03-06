package ru.tinkoff.fintech.homework.lesson3

import io.mockk.every
import io.mockk.spyk
import org.junit.Test
import org.junit.jupiter.api.assertAll
import ru.tinkoff.fintech.homework.lesson3.animals.Cat
import ru.tinkoff.fintech.homework.lesson3.animals.Mouse
import kotlin.test.assertEquals

class CatTest {
    @Test
    fun checkEatMouse() {
        val weightDifference = 123
        val mouse = spyk<Mouse> {
            every { weight } returns weightDifference
        }

        val cat = Cat("Борис", null)

        val previousWeight = cat.weight
        val expectedWeight = previousWeight + weightDifference

        cat.eatMouse(mouse)

        assertAll(
            "",
            { assert(!mouse.alive) },
            { assertEquals(expectedWeight, cat.weight) },
        )
    }
}