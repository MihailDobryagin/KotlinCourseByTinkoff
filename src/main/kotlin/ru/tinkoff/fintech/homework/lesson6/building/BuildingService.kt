package ru.tinkoff.fintech.homework.lesson6.building

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.building.dao.RoomDao
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room
import ru.tinkoff.fintech.homework.lesson6.utils.ValidationException

@Service
class BuildingService(
    private val roomDao: RoomDao
) {
    fun getRooms(): List<Room> {
        return roomDao.getRooms()
    }

    fun getRoom(id: Long): Room? = roomDao.getRoom(id)

    fun addRoom(name: String): Long {
        val room = Room(name = name)
        return roomDao.addRoom(room) ?: throw IllegalStateException("Не удалось добавить комнату")
    }

    fun moveWorker(from: Long?, to: Long?) {
        val roomFrom = from?.let { roomDao.getRoom(it) }
        val roomTo = to?.let { roomDao.getRoom(it) }

        validateMoveWorkerReq(from, roomFrom, to, roomTo)

        if (roomFrom != null) {
            val updatedRoomFrom = roomFrom.copy(countOfPeople = roomFrom.countOfPeople - 1)
            roomDao.updateRoom(updatedRoomFrom)
        }
        if (roomTo != null) {
            val updatedRoomTo = roomTo.copy(countOfPeople = roomTo.countOfPeople + 1)
            roomDao.updateRoom(updatedRoomTo)
        }
    }

    private fun validateMoveWorkerReq(from: Long?, roomFrom: Room?, to: Long?, roomTo: Room?) {
        val rooms = roomDao.getRooms()
        val error = when {
            from != null && roomFrom == null -> "Не существует комнаты с id $from"
            from != null && roomFrom!!.countOfPeople == 0 -> "В комнате $from нет людей"
            to != null && roomTo == null -> "Не существует комнаты с id $to"
            else -> null
        }
        if (error != null) throw ValidationException(error)
    }
}