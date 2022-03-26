package ru.tinkoff.fintech.homework.lesson5.currency

class CurrencyService(
    private val currencyRepository: CurrencyRepository = CurrencyRepository()
) {
    /**
     * Конвертация суммы в валюте в доллары
     */
    fun convert(value: Double, currency: String): Double? {
        return try {
            val exchangeRate = currencyRepository.getCurrencyExchangeRate(currency)
            if (exchangeRate == 0.0) null
            else value / exchangeRate!!
        } catch (e: NullPointerException) {
            null
        }
    }

    fun convert(value: String): Double? {
        val formatted = format(value)
        return if (formatted == null)
            null
        else
            convert(formatted.first, formatted.second)
    }

    private fun format(target: String): Pair<Double, String>? {

        return if (!target.matches(Regex("(([1-9][\\d\\.]*)|(0\\.[\\d.]*))[A-Z]{3}")))
            null
        else
            target.dropLast(3).toDouble() to target.takeLast(3)
    }
}