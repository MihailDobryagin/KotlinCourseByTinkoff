package ru.tinkoff.fintech.homework.lesson3

import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import ru.tinkoff.fintech.homework.lesson3.animals.Cat
import ru.tinkoff.fintech.homework.lesson3.animals.Mouse
import kotlin.properties.Delegates
import kotlin.test.assertEquals

class CatTest {
    private lateinit var cat: Cat
    private var previousWeight by Delegates.notNull<Int>()

    @BeforeEach
    fun init() {
        cat = spyk(Cat("Boris", null))
        previousWeight = cat.weight
    }

    @Test
    fun checkEatMouse() {
        val weightDifference = 123
        val mouse = spyk<Mouse> {
            every { weight } returns weightDifference
        }
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
        val expectedWeight = cat.weight

        cat.eatMouse(deadMouse)

        assertAll(
            "",
            { verify(exactly = 0) { cat.eat(any()) } },
            { assertEquals(expectedWeight, cat.weight) },
        )
    }
}