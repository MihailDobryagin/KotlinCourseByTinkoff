package ru.tinkoff.fintech.homework.lesson6.building.utils

import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.building.Room

@Component
data class RoomsDb(
    private val rooms: MutableMap<Long, Room> = mutableMapOf()
) {
    private var nextRoomId: Long = 0
    fun getRooms(): Map<Long, Room> = rooms.toMap()
    fun getRoom(roomId: Long): Room? = rooms[roomId]

    fun addRoom(name: String): Long {
        val room = Room(nextRoomId++, name, 0)
        rooms[room.id] = room
        return room.id
    }
}