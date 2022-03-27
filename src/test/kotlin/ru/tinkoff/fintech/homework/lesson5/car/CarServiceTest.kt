package ru.tinkoff.fintech.homework.lesson5.car

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertContentEquals


class CarServiceTest {
    private val carService = CarService()

    private val cars =
        listOf(
            Car("name1", "company1", CarType.SEDAN, 1.0, "USD", 12),
            Car("name3", "company3", CarType.SEDAN, 0.5, "USD", 13),
            Car("name4", "company4", CarType.LIMOUSINE, 2.0, "EUR", 13),
            Car("name2", "company2", CarType.SEDAN, 2.0, "USD", 12),
            Car("name5", "company5", CarType.LIMOUSINE, 50.0, "RUB", 1),
        )
    private val expectedOrderGettedByPriceSorting = listOf("5", "3", "1", "2", "4")

    @Test
    fun checkGroupingByTypes() {
        val expected = mapOf(
            CarType.SEDAN to cars.filter { it.type == CarType.SEDAN },
            CarType.LIMOUSINE to cars.filter { it.type == CarType.LIMOUSINE },
        )

        val actual = carService.groupByType(cars)

        assertEquals(expected.size, actual.size)
        assertContentEquals(expected[CarType.SEDAN], actual[CarType.SEDAN])
        assertContentEquals(expected[CarType.LIMOUSINE], actual[CarType.LIMOUSINE])

    }

    @Test
    fun checkSortingByPrice() {
        val actualOrder =
            carService.getDescriptionsSortedByPrice(cars)
                .map { "name(\\d)".toRegex().find(it)?.destructured?.toList()?.first() }

        assertEquals(expectedOrderGettedByPriceSorting, actualOrder)
    }

    @Test
    fun checkSortingByPriceWithSequences() {
        val actualOrder =
            carService.getDescriptionsSortedByPriceWithSequences(cars)
                .map { "name(\\d)".toRegex().find(it)?.destructured?.toList()?.first() }

        assertEquals(expectedOrderGettedByPriceSorting, actualOrder)
    }

    @Test
    fun get3NamesByPredicate() {
        val filter = { car: Car -> car.currency == "USD" }

        val expected = listOf("name1", "name2", "name3")

        val actual = carService.get3NamesByPredicate(cars, filter)

        assertAll(
            "Проверка на фильтрацию 3х названий по предикату",
            { assertTrue({ expected.containsAll(actual) }, "Отфильтрованные значения не включены в ожидаемые") },
            { assertTrue({ actual.containsAll(expected) }, "Ожидаемы значения не включены в отфильтрованные") },
        )
    }
}