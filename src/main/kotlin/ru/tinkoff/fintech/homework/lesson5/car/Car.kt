package ru.tinkoff.fintech.homework.lesson5.car

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
        if(!validate()) throw IllegalStateException()
    }

    fun validate(): Boolean {
        return checkName(name) && checkPrice(price) && fuelConsumption >= 0
    }

    private fun checkName(name: String): Boolean {
        return name.matches("[a-zA-Z\\d][ \\-\\w]*".toRegex())
    }

    private fun checkPrice(price: String): Boolean {
        return price.matches("(([1-9][\\d.]*)|(0\\.[\\d.]*))[A-Z]{3}".toRegex())
    }
}