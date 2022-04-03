package ru.tinkoff.fintech.homework.lesson6.building.db

import org.springframework.stereotype.Component

@Component
data class RoomsDb(
    private val rooms: MutableMap<Long, Room> = mutableMapOf()
) {
    private var nextRoomId: Long = 0
    fun getRooms(): Map<Long, Room> = rooms.toMap()

    fun getRoom(roomId: Long): Room? {
        return rooms[roomId]
    }

    fun addRoom(roomForAdd: Room): Long {
        val room = roomForAdd.copy(id = nextRoomId)
        rooms[nextRoomId++] = room
        return room.id!!
    }

    fun updateRoom(id: Long, room: Room): Boolean {
        return if (!rooms.contains(id)) false
        else {
            rooms[id] = room
            true
        }
    }
}