package ru.tinkoff.fintech.homework.lesson6.building

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BuildingService @Autowired constructor(
    private val rooms: MutableMap<Long, Room>,
) {

    companion object {
        private val logger = LoggerFactory.getLogger(BuildingService::class.java)
    }

    private var nextRoomId: Long = 0

    fun getRooms(): Map<Long, Room> {
        return rooms
    }

    fun getRoom(id: Long): Room? = rooms[id]

    fun addRoom(name: String): Long {
        val room = Room(nextRoomId++, name, 0)
        rooms[room.id] = room
        return room.id
    }

    fun moveWorker(from: Long?, to: Long?): Boolean {
        if (!validateMoveWorkerReq(from, to))
            return false

        val roomFrom = rooms[from]
        val roomTo = rooms[to]
        if (roomFrom != null)
            roomFrom.countOfPeople--
        if (roomTo != null)
            roomTo.countOfPeople--

        return true
    }

    private fun validateMoveWorkerReq(from: Long?, to: Long?): Boolean {
        return when {
            from == to -> {
                logger.error("Нельзя переместить человека из комнаты $from в $from")
                false
            }
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