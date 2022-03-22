package ru.tinkoff.fintech.homework.lesson5

import ru.tinkoff.fintech.homework.lesson5.car.Car
import ru.tinkoff.fintech.homework.lesson5.car.CarService
import ru.tinkoff.fintech.homework.lesson5.car.CarType

fun main() {
    val car = Car("123", "company", CarType.SEDAN, "123USD", 123)

    val carService = CarService()
}