package ru.tinkoff.fintech.homework.lesson5.car

import ru.tinkoff.fintech.homework.lesson5.car.utils.ValidationException

/**
 * @param name               Название модели
 * @param company            Марка машины
 * @param type               Тип кузова
 * @param price              Цена в формате <Число><Валюта>, где <Валюта> -- 1 символ валюты
 * @param fuelConsumption   Расход топлива за 1 км
 */
data class Car(
    val name: String,
    val company: String,
    val type: CarType,
    var price: String,
    val fuelConsumption: Int,
) {
    init {
        checkState()
    }

    fun validate(): Boolean {
        return try {
            checkState()
            true
        } catch (e: ValidationException) {
            false
        }
    }

    private fun checkState() {
        if (!checkName(name))
            throw ValidationException("Неверное название")
        if (!checkPrice(price))
            throw ValidationException("Неверный формат цены; Необходимый формаь - <число><3 цифры валюты>")
        if (!checkFuelConsumption(fuelConsumption))
            throw ValidationException("Неверное потребление топлива")
    }

    private fun checkName(name: String): Boolean {
        return name.matches("[a-zA-Z\\d][ \\-\\w]*".toRegex())
    }

    private fun checkPrice(price: String): Boolean {
        return price.matches("(([1-9][\\d.]*)|(0\\.[\\d.]*))[A-Z]{3}".toRegex())
    }

    private fun checkFuelConsumption(fuelConsumption: Int): Boolean {
        return fuelConsumption >= 0
    }
}