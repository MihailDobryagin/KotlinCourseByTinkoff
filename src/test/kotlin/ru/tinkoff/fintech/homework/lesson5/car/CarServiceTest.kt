package ru.tinkoff.fintech.homework.lesson5.car

import io.mockk.clearAllMocks
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertContentEquals
import kotlin.test.assertTrue


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

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkGroupingByTypes() {
        val expected = mapOf(
            CarType.SEDAN to cars.filter { it.type == CarType.SEDAN },
            CarType.LIMOUSINE to cars.filter { it.type == CarType.LIMOUSINE },
        )

        val actual = carService.groupByType(cars)

        assertAll(
            { assertEquals(expected.size, actual.size) },
            { assertContentEquals(expected[CarType.SEDAN], actual[CarType.SEDAN]) },
            { assertContentEquals(expected[CarType.LIMOUSINE], actual[CarType.LIMOUSINE]) },
        )
    }

    @Test
    fun checkSortingByPrice() {
        val expectedOrder = listOf("5", "3", "1", "2", "4")

        val actual = carService.getDescriptionsSortedByPrice(cars)

        assertTrue { checkResultOfSorting(actual, expectedOrder) }
    }

    @Test
    fun checkSortingByPriceWithSequences() {
        val expectedOrder = listOf("5", "3", "1", "2", "4")

        val actual = carService.getDescriptionsSortedByPriceWithSequences(cars)

        assertTrue { checkResultOfSorting(actual, expectedOrder) }
    }

    @Test
    fun get3NamesByPredicate() {
        val predicate = { car: Car ->
            car.currency == "USD"
        }
        val expected = listOf("name1", "name2", "name3")

        val actual = carService.get3NamesByPredicate(cars, predicate)

        assertAll(
            { assertTrue { expected.containsAll(actual) } },
            { assertTrue { actual.containsAll(expected) } },
        )
    }

    private fun checkResultOfSorting(actual: Collection<String>, expectedOrder: List<String>): Boolean {
        return actual.zip(expectedOrder).all { (car: String, number: String) ->
            car.matches(Regex(".*name$number.*"))
        }
    }
}