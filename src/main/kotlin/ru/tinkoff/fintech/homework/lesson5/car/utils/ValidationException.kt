package ru.tinkoff.fintech.homework.lesson5.car.utils

class ValidationException(
    override val message: String?
) : IllegalStateException()