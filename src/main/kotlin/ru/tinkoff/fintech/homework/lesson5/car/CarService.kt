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

    fun get3NamesByPredicate(cars: Collection<Car>, predicate: (Car) -> Boolean): List<String> {
        return cars
            .filter(predicate)
            .take(3)
            .map { it.name }
    }

    fun get3NamesByPredicateWithSequences(cars: Collection<Car>, predicate: (Car) -> Boolean): List<String> {
        return cars
            .asSequence()
            .filter(predicate)
            .take(3)
            .map { it.name }
            .toList()
    }

    fun newCarBuilder(
        name: String? = null,
        company: String? = null,
        type: CarType? = null,
        price: Double = 0.0,
        currency: String? = null,
        fuelConsumption: Int = 0,
    ): Car.Builder {
        return CarBuilderImpl(currencyService, name, company, type, price, currency, fuelConsumption)
    }

    private fun convertCarToItsDescription(car: Car): String {
        return """{"car": {"name": "${car.name}", "company": "${car.company}"}, """ +
                """"fuel-consumption": "${car.fuelConsumption}}"""
    }

    private class CarBuilderImpl(
        private val currencyService: CurrencyService,
        name: String? = null,
        company: String? = null,
        type: CarType? = null,
        price: Double = 0.0,
        currency: String? = null,
        fuelConsumption: Int = 0,
    ) : Car.Builder(name, company, type, price, currency, fuelConsumption) {
        override fun validate() {
            validateName(name)
            validateFuelConsumption(fuelConsumption)
        }

        private fun validateName(name: String?) {
            if (name == null || !name.matches("[a-zA-Z\\d][ \\-\\w]*".toRegex()))
                throw ValidationException("Неверное название")
        }

        private fun validateFuelConsumption(fuelConsumption: Int) {
            if (fuelConsumption < 0)
                throw ValidationException("Неверное потребление топлива")
        }

        private fun validateCurrency(currency: String?) {
            if (currency == null)
                throw ValidationException("Валюта не должна быть null")
            currencyService.validateCurrency(currency)
        }
    }
}