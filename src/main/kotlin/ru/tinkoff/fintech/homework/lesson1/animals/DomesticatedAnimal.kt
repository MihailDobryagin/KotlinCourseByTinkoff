package ru.tinkoff.fintech.homework.lesson1.animals

abstract class DomesticatedAnimal(
    name: String,
    weight: Int,
    color: String?,
) : SomeoneWhoCanHaveHome(name, weight, color)