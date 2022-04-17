package ru.tinkoff.fintech.homework.lesson6.building.dao

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room

@Component
@Profile("dev", "test")
class DevRoomDao(
    inputRooms: Map<Long, Room> = mapOf()
) : RoomDao {
    private val rooms = inputRooms.toMutableMap()
    private var nextRoomId: Long = 0

    override fun getRooms(): Map<Long, Room> = rooms.toMap()

    override fun getRoom(roomId: Long): Room? = rooms[roomId]

    override fun addRoom(newRoom: Room): Long {
        val room = newRoom.copy(id = nextRoomId++)
        rooms[room.id!!] = room
        return room.id
    }

    override fun updateRoom(room: Room) {
        if (!rooms.contains(room.id)) throw IllegalArgumentException("Нет помещениея с id ${room.id}")
        rooms[room.id!!] = room
    }
}