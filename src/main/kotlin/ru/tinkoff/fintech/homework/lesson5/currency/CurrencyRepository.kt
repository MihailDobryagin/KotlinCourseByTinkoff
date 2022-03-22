package ru.tinkoff.fintech.homework.lesson5.currency

internal class CurrencyRepository {
    private val currencies = mapOf<String, Double>(
        "USD" to 1.0,
        "RUB" to 110.0,
        "EUR" to 0.909,
        "GBP" to 0.7518797,
    )

    val availableCurrencies = currencies.keys

    fun getCurrencyExchangeRate(currency: String): Double? {
        return currencies[currency]
    }
}