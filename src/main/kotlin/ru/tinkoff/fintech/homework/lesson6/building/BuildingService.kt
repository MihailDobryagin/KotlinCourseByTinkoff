package ru.tinkoff.fintech.homework.lesson6.building

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.building.db.RoomsDb
import ru.tinkoff.fintech.homework.lesson6.building.dto.RoomDto

@Service
class BuildingService(
    private val roomsDb: RoomsDb
) {
    companion object {
        private val logger = LoggerFactory.getLogger(BuildingService::class.java)
    }

    fun getRooms(): Map<Long, RoomDto> {
        return roomsDb.getRooms()
    }

    fun getRoom(id: Long): RoomDto? = roomsDb.getRoom(id)

    fun addRoom(name: String): Long {
        val roomDto = RoomDto(name = name)
        return roomsDb.addRoom(roomDto)
    }

    fun moveWorker(from: Long?, to: Long?): Boolean {
        return if (!validateMoveWorkerReq(from, to)) false
        else {
            val roomFrom = from?.let { roomsDb.getRoom(it) }
            val roomTo = to?.let { roomsDb.getRoom(it) }
            if (roomFrom != null) {
                roomFrom.countOfPeople--
                roomsDb.updateRoom(from, roomFrom)
            }
            if (roomTo != null) {
                roomTo.countOfPeople++
                roomsDb.updateRoom(to, roomTo)
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