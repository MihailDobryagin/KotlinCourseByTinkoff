package ru.tinkoff.fintech.homework.lesson6.building

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.building.db.Room
import ru.tinkoff.fintech.homework.lesson6.building.db.RoomsDb
import utils.ValidationException

@Service
class BuildingService(
    private val roomsDb: RoomsDb
) {
    fun getRooms(): Map<Long, Room> {
        return roomsDb.getRooms()
    }

    fun getRoom(id: Long): Room? = roomsDb.getRoom(id)

    fun addRoom(name: String): Long {
        val room = Room(name = name)
        return roomsDb.addRoom(room)
    }

    fun moveWorker(from: Long?, to: Long?) {
        validateMoveWorkerReq(from, to)

        val roomFrom = from?.let { roomsDb.getRoom(it) }
        val roomTo = to?.let { roomsDb.getRoom(it) }

        if (roomFrom != null) {
            val updatedRoomFrom = roomFrom.copy(countOfPeople = roomFrom.countOfPeople - 1)
            roomsDb.updateRoom(from, updatedRoomFrom)
        }
        if (roomTo != null) {
            val updatedRoomTo = roomTo.copy(countOfPeople = roomTo.countOfPeople + 1)
            roomsDb.updateRoom(to, updatedRoomTo)
        }
    }

    private fun validateMoveWorkerReq(from: Long?, to: Long?) {
        val rooms = roomsDb.getRooms()
        val error = when {
            from != null && !rooms.contains(from) -> "Не существует комнаты с id $from"
            from != null && rooms[from]?.countOfPeople == 0 -> "В комнате $from нет людей"
            to != null && !rooms.contains(to) -> "Не существует комнаты с id $to"
            else -> null
        }
        if (error != null) throw ValidationException(error)
    }
}