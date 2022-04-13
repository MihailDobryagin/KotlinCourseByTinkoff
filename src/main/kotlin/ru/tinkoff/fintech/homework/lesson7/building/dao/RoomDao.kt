package ru.tinkoff.fintech.homework.lesson7.building.dao

import ru.tinkoff.fintech.homework.lesson7.building.entities.Room

interface RoomDao {
    fun getRooms(): Map<Long, Room>

    fun getRoom(roomId: Long): Room?

    fun addRoom(roomForAdd: Room): Long?

    fun updateRoom(id: Long, room: Room)
}