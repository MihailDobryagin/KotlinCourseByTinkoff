package ru.tinkoff.fintech.homework.lesson6.building

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

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
        return roomsDb.addRoom(name)
    }

    fun moveWorker(from: Long?, to: Long?): Boolean {
        return if (!validateMoveWorkerReq(from, to))
            false
        else {
            val rooms = roomsDb.getRooms()
            val roomFrom = rooms[from]
            val roomTo = rooms[to]
            if (roomFrom != null)
                roomFrom.countOfPeople--
            if (roomTo != null)
                roomTo.countOfPeople++
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