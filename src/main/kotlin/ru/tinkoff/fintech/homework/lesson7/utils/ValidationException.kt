package ru.tinkoff.fintech.homework.lesson7.utils

class ValidationException(
    override val message: String?
) : IllegalArgumentException()