package ru.tinkoff.fintech.homework.lesson5.car

import ru.tinkoff.fintech.homework.lesson5.currency.CurrencyService
import java.util.function.Predicate

class CarService {
    private val currencyService = CurrencyService()

    fun getDescriptionsSortedByPrice(cars: Collection<Car>): Collection<String> {
        return cars
            .sortedBy {
                convertPrice(it.price)
            }
            .map(::convertCarToItsDescription)
    }

    fun getDescriptionsSortedByPriceWithSequences(cars: Collection<Car>): Collection<String> {
        return cars
            .asSequence()
            .sortedBy { convertPrice(it.price) }
            .map(::convertCarToItsDescription)
            .toList()
    }

    fun groupByType(cars: Collection<Car>): Map<CarType, Collection<Car>> {
        return cars.groupBy { it.type }
    }

    fun get3NamesByPredicate(cars: Collection<Car>, predicate: Predicate<Car>): Collection<String> {
        return cars
            .filter { predicate.test(it) }
            .take(3)
            .map { it.name }
    }

    fun get3NamesByPredicateWithSequences(cars: Collection<Car>, predicate: Predicate<Car>): Collection<String> {
        return cars
            .asSequence()
            .filter(predicate::test)
            .take(3)
            .map { it.name }
            .toList()
    }

    private fun convertCarToItsDescription(car: Car): String {
        return "" +
                "{\"Car\": {" +
                formatParameter("name", car.name) + " ," +
                formatParameter("company", car.company) + " ," +
                formatParameter("waste of fuel(l/km)", car.fuelConsumption) +
                "}"
    }

    private fun formatParameter(parameterName: String, value: Any): String {
        return "\"$parameterName\": \"$value\""
    }

    private fun convertPrice(price: String): Double? {
        return currencyService.convert(price)
    }
}