package ru.tinkoff.fintech.homework.lesson5.car

class Car private constructor(
    val name: String,
    val company: String,
    val type: CarType,
    var price: Double,
    var currency: String,
    val fuelConsumption: Int
) {
    abstract class Builder(
        var name: String? = null,
        var company: String? = null,
        var type: CarType? = null,
        var price: Double = 0.0,
        var currency: String? = null,
        var fuelConsumption: Int = 0,
    ) {
        fun withName(name: String): Builder {
            this.name = name
            return this
        }

        fun withCompany(company: String): Builder {
            this.company = company
            return this
        }

        fun withType(type: CarType): Builder {
            this.type = type
            return this
        }

        fun withPrice(price: Double): Builder {
            this.price = price
            return this
        }

        fun withCurrency(currency: String): Builder {
            this.currency = currency
            return this
        }

        fun withFuelConsumption(fuelConsumption: Int): Builder {
            this.fuelConsumption = fuelConsumption
            return this
        }

        fun build(): Car {
            if (checkForNulls()) throw NullPointerException("Все поля не должны быть null")
            validate()
            return Car(name!!, company!!, type!!, price, currency!!, fuelConsumption)
        }

        protected abstract fun validate()

        private fun checkForNulls(): Boolean {
            return listOf(name, company, type, price, currency, fuelConsumption).any { it == null }
        }
    }
}