package ru.tinkoff.fintech.homework.lesson6.utils

class ValidationException(
    override val message: String?
) : IllegalArgumentException()