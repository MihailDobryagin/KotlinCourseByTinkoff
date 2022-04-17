package ru.tinkoff.fintech.homework.lesson6.building.dao

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room
import ru.tinkoff.fintech.homework.lesson6.building.repository.RoomsRepository

@Component
@Profile("jpa")
class JpaRoomDao(
    private val roomsRepository: RoomsRepository,
) : RoomDao {
    override fun getRooms(): Map<Long, Room> {
        return roomsRepository.findAll().associateBy { it.id!! }
    }

    override fun getRoom(roomId: Long): Room? {
        return roomsRepository.findById(roomId).orElse(null)
    }

    override fun addRoom(newRoom: Room): Long? {
        return roomsRepository.save(newRoom).id
    }

    override fun updateRoom(room: Room) {
        roomsRepository.save(room)
    }
}