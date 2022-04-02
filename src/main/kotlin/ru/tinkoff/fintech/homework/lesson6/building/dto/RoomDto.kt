package ru.tinkoff.fintech.homework.lesson6.building.dto

data class RoomDto(
    var id: Long? = null,
    var name: String? = null,
    var countOfPeople: Int = 0
)