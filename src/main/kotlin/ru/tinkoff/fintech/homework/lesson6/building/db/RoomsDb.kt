package ru.tinkoff.fintech.homework.lesson6.building.db

import org.springframework.stereotype.Component

@Component
class RoomsDb(
    inputRooms: Map<Long, Room> = mapOf()
) {
    private val rooms = inputRooms.toMutableMap()
    private var nextRoomId: Long = 0

    fun getRooms(): Map<Long, Room> = rooms.toMap()

    fun getRoom(roomId: Long): Room? = rooms[roomId]

    fun addRoom(roomForAdd: Room): Long {
        val room = roomForAdd.copy(id = nextRoomId++)
        rooms[room.id!!] = room
        return room.id
    }

    fun updateRoom(id: Long, room: Room): Boolean {
        return if (!rooms.contains(id)) false
        else {
            rooms[id] = room
            true
        }
    }
}