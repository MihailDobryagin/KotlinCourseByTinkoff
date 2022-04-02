package ru.tinkoff.fintech.homework.lesson6.building

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import ru.tinkoff.fintech.homework.lesson6.building.request.dto.MoveWorkerDto
import ru.tinkoff.fintech.homework.lesson6.building.response.ErrorResponse
import ru.tinkoff.fintech.homework.lesson6.building.response.SimpleResponse
import ru.tinkoff.fintech.homework.lesson6.building.response.SuccessResponse

@RestController
@RequestMapping("building")
class BuildingController(
    private val buildingService: BuildingService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(BuildingController::class.java)
    }

    @GetMapping("rooms")
    fun getRooms(): Map<Long, Room> {
        logger.info("Запрос на получение помещений")
        return buildingService.getRooms()
    }

    @GetMapping("room/{roomId}")
    fun getRoom(
        @PathVariable roomId: Long,
    ): Room? {
        logger.info("Запрос на получение помещения")
        return buildingService.getRoom(roomId)
    }

    @GetMapping("rooms/add")
    fun addRoom(@RequestParam name: String): Long {
        logger.info("""Запрос на добавление помещения "$name"""")
        return buildingService.addRoom(name)
    }

    @PostMapping("workers/move")
    fun moveWorker(
        @RequestBody moveWorkerDto: MoveWorkerDto,
    ): SimpleResponse {
        val from = moveWorkerDto.from
        val to = moveWorkerDto.to
        logger.info("Запрос на перемещение работника из $from в $to")
        val movingResult = buildingService.moveWorker(from, to)
        return if (movingResult) SuccessResponse() else ErrorResponse()
    }
}