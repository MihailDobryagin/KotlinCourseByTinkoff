package ru.tinkoff.fintech.homework.lesson6.building

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.building.db.Room
import ru.tinkoff.fintech.homework.lesson6.building.db.RoomsDb

@Service
class BuildingService(
    private val roomsDb: RoomsDb
) {
    companion object {
        private val logger = LoggerFactory.getLogger(BuildingService::class.java)
    }

    fun getRooms(): Map<Long, Room> {
        return roomsDb.getRooms()
    }

    fun getRoom(id: Long): Room? = roomsDb.getRoom(id)

    fun addRoom(name: String): Long {
        val room = Room(name = name)
        return roomsDb.addRoom(room)
    }

    fun moveWorker(from: Long?, to: Long?): Boolean {
        return if (!validateMoveWorkerReq(from, to)) false
        else {
            val roomFrom = from?.let { roomsDb.getRoom(it) }
            val roomTo = to?.let { roomsDb.getRoom(it) }

            if (roomFrom != null) {
                var countOfPeople = roomFrom.countOfPeople
                countOfPeople--
                val updatedRoomFrom = Room(from, roomFrom.name, countOfPeople)
                roomsDb.updateRoom(from, updatedRoomFrom)
            }
            if (roomTo != null) {
                var countOfPeople = roomTo.countOfPeople
                countOfPeople++
                val updatedRoomTo = Room(to, roomTo.name, countOfPeople)
                roomsDb.updateRoom(to, updatedRoomTo)
            }
            true
        }
    }

    private fun validateMoveWorkerReq(from: Long?, to: Long?): Boolean {
        val rooms = roomsDb.getRooms()
        val error = when {
            from != null && !rooms.contains(from) -> "Не существует комнаты с id $from"
            from != null && rooms[from]?.countOfPeople == 0 -> "В комнате $from нет людей"
            to != null && !rooms.contains(to) -> "Не существует комнаты с id $to"
            else -> null
        }
        error?.let(logger::error)
        return error == null
    }
}