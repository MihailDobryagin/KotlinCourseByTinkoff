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
            Arguments.of("12\n3"),
            Arguments.of("a,a"),
            Arguments.of("a$"),
            Arguments.of(" a"),
            Arguments.of("_"),
        )

        @JvmStatic
        fun `valid names`() = listOf(
            Arguments.of("123"),
            Arguments.of("aa-a"),
            Arguments.of("a_"),
            Arguments.of("a-a"),
        )

        @JvmStatic
        fun `invalid prices`() = listOf(
            Arguments.of("1"),
            Arguments.of("USD1"),
            Arguments.of("1US"),
            Arguments.of("1usd"),
            Arguments.of("USD"),
        )
    }
}