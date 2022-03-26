package ru.tinkoff.fintech.homework.lesson5.currency

class CurrencyService(
    private val currencyRepository: CurrencyRepository = CurrencyRepository()
) {
    /**
     * Конвертация суммы в валюте в доллары
     */
    fun convert(value: Double, currency: String): Double? {

        val exchangeRate = currencyRepository.getCurrencyExchangeRate(currency)
        return if (exchangeRate == null) null
        else
            value / exchangeRate
    }

    fun convert(value: String): Double? {
        val formatted = format(value)
        return if (formatted == null)
            null
        else
            convert(formatted.first, formatted.second)
    }

    private fun format(target: String): Pair<Double, String>? {

        val naturalNumber = "[1-9]"
        val delimiter = "\\."
        val numberGreaterThanOneByAbs = "$naturalNumber$delimiter?\\d*"
        val numberLessThanOneByAbs = "0$delimiter\\d*"
        val numberPattern = "($numberGreaterThanOneByAbs|$numberLessThanOneByAbs)"

        val currencyPattern = "[A-Z]{3}"

        val pattern = "$numberPattern$currencyPattern"

        return if (!target.matches(pattern.toRegex()))
            null
        else
            target.dropLast(3).toDouble() to target.takeLast(3)
    }
}