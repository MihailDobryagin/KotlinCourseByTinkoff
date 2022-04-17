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
        return roomsRepository.findAllBy().associateBy { it.id!! }
    }

    override fun getRoom(roomId: Long): Room? {
        return roomsRepository.findById(roomId).orElse(null)
    }

    override fun addRoom(roomForAdd: Room): Long? {
        return roomsRepository.save(roomForAdd).id
    }

    override fun updateRoom(room: Room) {
        roomsRepository.save(room)
    }
}