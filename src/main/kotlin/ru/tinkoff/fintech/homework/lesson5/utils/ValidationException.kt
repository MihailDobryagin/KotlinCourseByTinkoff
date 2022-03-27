package ru.tinkoff.fintech.homework.lesson5.utils

class ValidationException(
    override val message: String?
) : IllegalStateException()