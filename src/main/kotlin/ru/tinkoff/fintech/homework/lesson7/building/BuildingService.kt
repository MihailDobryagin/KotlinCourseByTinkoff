package ru.tinkoff.fintech.homework.lesson7.building

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson7.building.dao.DevRoomDao
import ru.tinkoff.fintech.homework.lesson7.building.entities.Room
import ru.tinkoff.fintech.homework.lesson7.utils.ValidationException

@Service
class BuildingService(
    private val devRoomDao: DevRoomDao
) {
    fun getRooms(): Map<Long, Room> {
        return devRoomDao.getRooms()
    }

    fun getRoom(id: Long): Room? = devRoomDao.getRoom(id)

    fun addRoom(name: String): Long {
        val room = Room(name = name)
        return devRoomDao.addRoom(room)
    }

    fun moveWorker(from: Long?, to: Long?) {
        validateMoveWorkerReq(from, to)

        val roomFrom = from?.let { devRoomDao.getRoom(it) }
        val roomTo = to?.let { devRoomDao.getRoom(it) }

        if (roomFrom != null) {
            val updatedRoomFrom = roomFrom.copy(countOfPeople = roomFrom.countOfPeople - 1)
            devRoomDao.updateRoom(from, updatedRoomFrom)
        }
        if (roomTo != null) {
            val updatedRoomTo = roomTo.copy(countOfPeople = roomTo.countOfPeople + 1)
            devRoomDao.updateRoom(to, updatedRoomTo)
        }
    }

    private fun validateMoveWorkerReq(from: Long?, to: Long?) {
        val rooms = devRoomDao.getRooms()
        val error = when {
            from != null && !rooms.contains(from) -> "Не существует комнаты с id $from"
            from != null && rooms[from]?.countOfPeople == 0 -> "В комнате $from нет людей"
            to != null && !rooms.contains(to) -> "Не существует комнаты с id $to"
            else -> null
        }
        if (error != null) throw ValidationException(error)
    }
}