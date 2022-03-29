package ru.tinkoff.fintech.homework.lesson6.building.response

open class SimpleResponse(
    val success: Boolean,
    open val message: String = "",
)