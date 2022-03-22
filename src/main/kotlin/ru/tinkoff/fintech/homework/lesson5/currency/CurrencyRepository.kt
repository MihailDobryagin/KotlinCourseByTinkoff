package ru.tinkoff.fintech.homework.lesson5.currency

internal class CurrencyRepository {
    private val currencies = mapOf<String, Double>(
        "USD" to 1.0,
        "RUB" to 100.0,
        "EUR" to 1.1,
        "GBP" to 1.33,
    )

    val availableCurrencies = currencies.keys

    fun getCurrencyExchangeRate(currency: String): Double? {
        return currencies[currency]
    }
}