package ru.tinkoff.fintech.homework.lesson7.building.db

data class Room(
    val id: Long? = null,
    val name: String,
    val countOfPeople: Int = 0,
)
