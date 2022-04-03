package ru.tinkoff.fintech.homework.lesson6.building.response

data class SimpleResponse(
    val success: Boolean,
    val message: String = "",
)