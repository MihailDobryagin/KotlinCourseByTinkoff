package ru.tinkoff.fintech.homework.lesson6.building.db

/**
 * Комнаты только для хранения в БД
 */
data class Room(
    val id: Long? = null,
    val name: String,
    val countOfPeople: Int = 0,
)
