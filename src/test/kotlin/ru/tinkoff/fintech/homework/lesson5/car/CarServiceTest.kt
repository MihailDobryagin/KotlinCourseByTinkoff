package ru.tinkoff.fintech.homework.lesson5.car

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertContentEquals


class CarServiceTest {

    val cars = spyk(
        listOf(
            Car("name2", "company2", CarType.SEDAN, "2USD", 12),
            Car("name1", "company1", CarType.SEDAN, "1USD", 12),
            Car("name3", "company3", CarType.SEDAN, "0.5USD", 13),
            Car("name4", "company4", CarType.LIMOUSINE, "2EUR", 13),
            Car("name5", "company5", CarType.LIMOUSINE, "5RUB", 1),
        )
    )

    val carService = spyk(CarService())

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkGroupingByTypes() {
        val actual = carService.groupByType(cars)
        val expected = mapOf(
            CarType.SEDAN to cars.filter { it.type == CarType.SEDAN },
            CarType.LIMOUSINE to cars.filter { it.type == CarType.LIMOUSINE },
        )

        assertAll(
            { assertEquals(expected.size, actual.size) },
            { assertContentEquals(expected[CarType.SEDAN], actual[CarType.SEDAN]) },
            { assertContentEquals(expected[CarType.LIMOUSINE], actual[CarType.LIMOUSINE]) },
        )
    }
}