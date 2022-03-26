package ru.tinkoff.fintech.homework.lesson6.building

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BuildingService @Autowired constructor(
    private val rooms: Map<String, Int>,
) {
    fun getRooms(): Map<String, Int> {
        return rooms
    }
}