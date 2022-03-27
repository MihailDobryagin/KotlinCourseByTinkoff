package ru.tinkoff.fintech.homework.lesson5.car

data class Car(
    val name: String,
    val company: String,
    val type: CarType,
    var price: Double,
    var currency: String,
    val fuelConsumption: Int
)