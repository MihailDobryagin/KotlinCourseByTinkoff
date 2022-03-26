package ru.tinkoff.fintech.homework.lesson5.car

import io.mockk.clearAllMocks
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CarTest {

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @ParameterizedTest
    @MethodSource("valid names")
    fun checkMakingCarWithValidName(name: String) {
        assertDoesNotThrow { Car(name, "company", CarType.SEDAN, "1USD", 1) }
    }

    @ParameterizedTest
    @MethodSource("invalid names")
    fun checkMakingCarWithInvalidName(name: String) {
        assertThrows<IllegalStateException> { Car(name, "company", CarType.SEDAN, "1USD", 1) }
    }

    @Test
    fun checkMakingCarWithValidPrice() {
        assertDoesNotThrow { Car("name", "company", CarType.SEDAN, "1.345RUB", 1) }
    }

    @ParameterizedTest
    @MethodSource("invalid prices")
    fun checkMakingCarWithInValidPrice(price: String) {
        assertThrows<IllegalStateException> { Car("name", "company", CarType.SEDAN, price, 1) }
    }

    private companion object {
        @JvmStatic
        fun `invalid names`() = listOf(
            "12\n3",
            "a,a",
            "a$",
            " a",
            "_",
        ).map { Arguments.of(it) }

        @JvmStatic
        fun `valid names`(): List<Arguments> = listOf(
            "123",
            "aa-a",
            "a_",
            "a-a"
        ).map { Arguments.of(it) }

        @JvmStatic
        fun `invalid prices`() = listOf(
            "1",
            "USD1",
            "1US",
            "1usd",
            "USD",
        ).map { Arguments.of(it) }
    }
}