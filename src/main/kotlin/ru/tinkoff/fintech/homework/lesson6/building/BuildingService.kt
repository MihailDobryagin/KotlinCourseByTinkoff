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

    private var nextRoomId: Long = 0

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
        return when {
            from != null && !rooms.contains(from) -> {
                logger.error("Не существует комнаты с id $from")
                false
            }
            from != null && rooms[from]?.countOfPeople == 0 -> {
                logger.error("В комнате $from нет людей")
                false
            }
            to != null && !rooms.contains(to) -> {
                logger.error("Не существует комнаты с id $to")
                false
            }
            else -> true
        }
    }
}