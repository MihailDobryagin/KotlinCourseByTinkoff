package ru.tinkoff.fintech.homework.lesson6.building.db

import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.building.dto.RoomDto

@Component
data class RoomsDb(
    private val rooms: MutableMap<Long, Room> = mutableMapOf()
) {
    private var nextRoomId: Long = 0
    fun getRooms(): Map<Long, RoomDto> = rooms.mapValues {
        RoomDto(it.key, it.value.name, it.value.countOfPeople)
    }.toMap()

    fun getRoom(roomId: Long): RoomDto? {
        return rooms[roomId]?.let {
            RoomDto(it.id, it.name, it.countOfPeople)
        }
    }

    fun addRoom(roomDto: RoomDto): Long {
        val room = Room(nextRoomId++, roomDto.name!!, roomDto.countOfPeople)
        rooms[room.id] = room
        return room.id
    }

    fun updateRoom(id: Long, roomDto: RoomDto): Boolean {
        return if (!rooms.contains(id)) false
        else {
            rooms[id] = Room(id, roomDto.name!!, roomDto.countOfPeople)
            true
        }
    }
}