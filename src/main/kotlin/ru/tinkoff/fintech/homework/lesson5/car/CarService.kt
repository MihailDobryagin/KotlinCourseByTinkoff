package ru.tinkoff.fintech.homework.lesson5.car

import ru.tinkoff.fintech.homework.lesson5.currency.CurrencyService

class CarService {
    private val currencyService = CurrencyService()

    fun getDescriptionsSortedByPrice(cars: Collection<Car>): Collection<String> {
        return cars
            .sortedBy {
                convertPrice(it.price)
            }
            .map(this::convertCarToItsDescription)
    }

    private fun convertCarToItsDescription(car: Car): String {
        return "" +
                "{\"Car\": {" +
                formatParameter("name", car.name) + " ," +
                formatParameter("company", car.company) + " ," +
                formatParameter("waste of fuel(l/km)", car.wasteOfFuelPerKm) +
                "}"
    }

    private fun formatParameter(parameterName: String, value: Any): String {
        return "\"$parameterName\": \"$value\""
    }

    private fun convertPrice(price: String): Double? {
        return currencyService.convert(price)
    }
}