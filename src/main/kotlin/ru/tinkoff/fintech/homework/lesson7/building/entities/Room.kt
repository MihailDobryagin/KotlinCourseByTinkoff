package ru.tinkoff.fintech.homework.lesson7.building.entities

data class Room(
    val id: Long? = null,
    val name: String,
    val countOfPeople: Int = 0,
)
