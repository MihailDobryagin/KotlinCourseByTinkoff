package ru.tinkoff.fintech.homework.lesson6.building

data class Room(
    val id: Long,
    val name: String,
    var countOfPeople: Int = 0,
)
