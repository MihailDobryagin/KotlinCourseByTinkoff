package ru.tinkoff.fintech.homework.lesson6.building

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BuildingService @Autowired constructor(
    private val rooms: MutableMap<Long, Room>,
) {
    private var nextRoomId: Long = 0

    fun getRooms(): Map<Long, Room> {
        return rooms
    }

    fun addRoom(name: String): Long {
        val room = Room(nextRoomId++, name, 0)
        rooms[room.id] = room
        return room.id
    }
}