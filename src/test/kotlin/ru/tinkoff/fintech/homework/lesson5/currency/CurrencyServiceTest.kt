package ru.tinkoff.fintech.homework.lesson5.currency

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import ru.tinkoff.fintech.homework.lesson5.car.utils.ValidationException

class CurrencyServiceTest {
    @MockK
    private lateinit var currencyRepository: CurrencyRepository

    @SpyK
    @InjectMockKs
    private lateinit var currencyService: CurrencyService

    @BeforeEach
    fun beforeEach() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkValidationOfValidPrice() {
        assertDoesNotThrow { currencyService.validateCurrency("QWE") }
    }

    @Test
    fun checkValidationOfInvalidCurrency() {
        assertThrows<ValidationException> { currencyService.validateCurrency("QwE") }
    }

    @Test
    fun checkConverting() {
        every { currencyRepository.getCurrencyExchangeRate("QWE") } returns 10.0
        val value = 20.0
        val expected = 2.0

        val actual = currencyService.convert(value, "QWE")

        assertEquals(expected, actual)
    }

    @Test
    fun checkConvertingNonExistingCurrency() {
        every { currencyRepository.getCurrencyExchangeRate(any()) } returns null

        val actual = currencyService.convert(1.0, "QWE")

        assertNull(actual)
    }
}