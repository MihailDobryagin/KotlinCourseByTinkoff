package ru.tinkoff.fintech.homework.lesson5.car

/**
 * @param name               Название модели
 * @param company            Марка машины
 * @param type               Тип кузова
 * @param price              Цена в формате <Число><Валюта>, где <Валюта> -- 1 символ валюты
 * @param wasteOfFuelPerKm   Расход топлива за 1 км
 */
data class Car(
    val name: String,
    val company: String,
    val type: CarType,
    var price: String,
    val wasteOfFuelPerKm: Int,
) {
    init {
        if (!checkForEnglishLang(
                listOf(
                    name,
                    company
                )
            )
        ) throw IllegalArgumentException("Названия должны быть составлены из латинских букв")
        if (!validatePrice(price)) throw IllegalArgumentException("Неверный формат цены")
        if (wasteOfFuelPerKm < 0) throw IllegalArgumentException("Расход топлива не может быть отрицательным")
    }

    private fun checkForEnglishLang(target: Collection<String>): Boolean {
        return target.any {
            it.matches(Regex("[\\s\\w\\d]+"))
        }
    }

    private fun validatePrice(price: String): Boolean {
        return price.matches(Regex("(([1-9][\\d.]*)|(0\\.[\\d.]*))[A-Z]{3}"))
    }
}