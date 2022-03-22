package ru.tinkoff.fintech.homework.lesson5.car

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.util.function.Predicate
import kotlin.test.assertContentEquals
import kotlin.test.assertTrue


class CarServiceTest {
    private val carService = spyk(CarService())

    private val cars = spyk(
        listOf(
            Car("name1", "company1", CarType.SEDAN, "1USD", 12),
            Car("name3", "company3", CarType.SEDAN, "0.5USD", 13),
            Car("name4", "company4", CarType.LIMOUSINE, "2EUR", 13),
            Car("name2", "company2", CarType.SEDAN, "2USD", 12),
            Car("name5", "company5", CarType.LIMOUSINE, "50RUB", 1),
        )
    )

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

    @Test
    fun checkSortingByPrice() {
        val actual = carService.getDescriptionsSortedByPrice(cars)
        val expectedOrder = listOf("5", "3", "1", "2", "4")
        assertTrue { checkResultOfSorting(actual, expectedOrder) }
    }

    @Test
    fun checkSortingByPriceWithSequences() {
        val actual = carService.getDescriptionsSortedByPriceWithSequences(cars)
        val expectedOrder = listOf("5", "3", "1", "2", "4")
        assertTrue { checkResultOfSorting(actual, expectedOrder) }
    }

    @Test
    fun get3NamesByPredicate() {
        val predicate = Predicate<Car> {
            it.price.takeLast(3) == "USD"
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