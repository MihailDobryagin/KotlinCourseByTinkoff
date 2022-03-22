package ru.tinkoff.fintech.homework.lesson5.currency

class CurrencyService {
    private val currencyRepository = CurrencyRepository()

    /**
     * Конвертация суммы в валюте в доллары
     */
    fun convert(value: Double, currency: String): Double? {
        val exchangeRate = currencyRepository.getCurrencyExchangeRate(currency) ?: return null
        return value / exchangeRate
    }

    fun convert(value: String): Double? {
        val formatted = format(value) ?: return null
        return convert(formatted.first, formatted.second)
    }

    private fun format(target: String): Pair<Double, String>? {
        if (!target.matches(Regex("(([1-9][\\d.]*)|(0\\.[\\d.]*))[A-Z]{3}"))) return null

        return target.dropLast(3).toDouble() to target.takeLast(3)
    }
}