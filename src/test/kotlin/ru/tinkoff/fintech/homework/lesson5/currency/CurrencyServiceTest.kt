package ru.tinkoff.fintech.homework.lesson5.currency

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
    fun checkConvertingNonExistingCurrency() {
        every { currencyRepository.getCurrencyExchangeRate(any()) } returns null

        val actual = currencyService.convert(1.0, "QWE")

        assertNull(actual)
    }
}