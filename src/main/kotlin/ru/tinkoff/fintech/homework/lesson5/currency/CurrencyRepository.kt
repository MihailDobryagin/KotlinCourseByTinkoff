package ru.tinkoff.fintech.homework.lesson5.currency

class CurrencyRepository(
    private val currencies: Map<String, Double> = defaultCurrencies
) {
    init {
        if (currencies.values.any { it <= 0.0 }) throw IllegalStateException("Значение курсов должны быть положительными")
    }

    val availableCurrencies = currencies.keys

    fun getCurrencyExchangeRate(currency: String): Double? {
        return currencies[currency]
    }
}

private val defaultCurrencies = mapOf(
    "USD" to 1.0,
    "RUB" to 110.0,
    "EUR" to 0.909,
    "GBP" to 0.7518797,
)