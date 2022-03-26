package ru.tinkoff.fintech.homework.lesson5.car

import ru.tinkoff.fintech.homework.lesson5.car.utils.ValidationException
import ru.tinkoff.fintech.homework.lesson5.currency.CurrencyService

class CarService(
    private val currencyService: CurrencyService = CurrencyService()
) {
    fun getDescriptionsSortedByPrice(cars: Collection<Car>): List<String> {
        return cars
            .sortedBy { currencyService.convert(it.price, it.currency) }
            .map(::convertCarToItsDescription)
    }

    fun getDescriptionsSortedByPriceWithSequences(cars: Collection<Car>): List<String> {
        return cars
            .asSequence()
            .sortedBy { currencyService.convert(it.price, it.currency) }
            .map(::convertCarToItsDescription)
            .toList()
    }

    fun groupByType(cars: Collection<Car>): Map<CarType, Collection<Car>> {
        return cars.groupBy { it.type }
    }

    fun get3NamesByPredicate(cars: Collection<Car>, predicate: (Car) -> Boolean): Collection<String> {
        return cars
            .filter(predicate)
            .take(3)
            .map { it.name }
    }

    fun get3NamesByPredicateWithSequences(cars: Collection<Car>, predicate: (Car) -> Boolean): Collection<String> {
        return cars
            .onEach(::validateCar)
            .asSequence()
            .filter(predicate)
            .take(3)
            .map { it.name }
            .toList()
    }

    fun validateName(name: String) {
        if (!name.matches("[a-zA-Z\\d][ \\-\\w]*".toRegex()))
            throw ValidationException("Неверное название")
    }

    private fun convertCarToItsDescription(car: Car): String {
        validateCar(car)

        return """{"car": {"name": "${car.name}", "company": "${car.company}"}, """ +
                """"fuel-consumption": "${car.fuelConsumption}}"""
    }

    private fun validateFuelConsumption(fuelConsumption: Int) {
        if (fuelConsumption < 0)
            throw ValidationException("Неверное потребление топлива")
    }

    private fun validateCar(car: Car) {
        validateName(car.name)
        validateFuelConsumption(car.fuelConsumption)
    }
}