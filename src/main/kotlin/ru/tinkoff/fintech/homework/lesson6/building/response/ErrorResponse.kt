package ru.tinkoff.fintech.homework.lesson6.building.response

data class ErrorResponse(
    override val message: String = "",
) : SimpleResponse(false, message)