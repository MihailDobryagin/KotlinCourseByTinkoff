package ru.tinkoff.fintech.homework.lesson6.building

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.building.dao.RoomDao
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room
import ru.tinkoff.fintech.homework.lesson6.utils.ValidationException

@Service
class BuildingService(
    private val roomDao: RoomDao
) {
    fun getRooms(): Map<Long, Room> {
        return roomDao.getRooms()
    }

    fun getRoom(id: Long): Room? = roomDao.getRoom(id)

    fun addRoom(name: String): Long {
        val room = Room(name = name)
        return roomDao.addRoom(room) ?: throw IllegalStateException()
    }

    fun moveWorker(from: Long?, to: Long?) {
        validateMoveWorkerReq(from, to)

        val roomFrom = from?.let { roomDao.getRoom(it) }
        val roomTo = to?.let { roomDao.getRoom(it) }

        if (roomFrom != null) {
            val updatedRoomFrom = roomFrom.copy(countOfPeople = roomFrom.countOfPeople - 1)
            roomDao.updateRoom(updatedRoomFrom)
        }
        if (roomTo != null) {
            val updatedRoomTo = roomTo.copy(countOfPeople = roomTo.countOfPeople + 1)
            roomDao.updateRoom(updatedRoomTo)
        }
    }

    private fun validateMoveWorkerReq(from: Long?, to: Long?) {
        val rooms = roomDao.getRooms()
        val error = when {
            from != null && !rooms.contains(from) -> "Не существует комнаты с id $from"
            from != null && rooms[from]?.countOfPeople == 0 -> "В комнате $from нет людей"
            to != null && !rooms.contains(to) -> "Не существует комнаты с id $to"
            else -> null
        }
        if (error != null) throw ValidationException(error)
    }
}