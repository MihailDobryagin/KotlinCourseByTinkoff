package ru.tinkoff.fintech.homework.lesson5.currency

import ru.tinkoff.fintech.homework.lesson5.utils.ValidationException

class CurrencyService(
    private val currencyRepository: CurrencyRepository = CurrencyRepository()
) {
    /**
     * Конвертация суммы в валюте в доллары
     */
    fun convert(value: Double, currency: String): Double? {
        validateCurrency(currency)

        val exchangeRate = currencyRepository.getCurrencyExchangeRate(currency)

        return if (exchangeRate == null) null else value / exchangeRate
    }

    fun validateCurrency(currency: String) {
        if (!currency.matches("[A-Z]{3}".toRegex()))
            throw ValidationException("Неверный формат валюты; Необходимо 3 заглавных буквы")
    }
}