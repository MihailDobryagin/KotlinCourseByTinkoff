package ru.tinkoff.fintech.homework.lesson3

import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
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
            { assertEquals(1, cat.countOfEatenMouses) }
        )
    }

    @Test
    fun checkEatMouseWhenMouseIsDead() {
        val deadMouse = spyk<Mouse> {
            every { alive } returns false
        }

        val cat = spyk(Cat("Борис", null))
        val expectedWeight = cat.weight

        cat.eatMouse(deadMouse)

        assertAll(
            "",
            { verify(exactly = 0) { cat.eat(any()) } },
            { assertEquals(expectedWeight, cat.weight) },
        )
    }
}