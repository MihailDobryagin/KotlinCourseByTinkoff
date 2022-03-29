package ru.tinkoff.fintech.homework.lesson6.building

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.tinkoff.fintech.homework.lesson6.building.request.dto.MoveWorkerDto

@RestController
@RequestMapping("building")
class BuildingController @Autowired constructor(
    private val buildingService: BuildingService

) {
    companion object {
        private val logger = LoggerFactory.getLogger(BuildingController::class.java)
    }

    @GetMapping("rooms")
    @ResponseBody
    fun getRooms(): Map<Long, Room> {
        return buildingService.getRooms()
    }

    @GetMapping("rooms/add")
    @ResponseBody
    fun addRoom(@RequestParam name: String): Long {
        return buildingService.addRoom(name)
    }

    @PostMapping("workers/move")
    @ResponseBody
    fun moveWorker(
        @RequestBody moveWorkerDto: MoveWorkerDto,
    ) {
        val from = moveWorkerDto.from
        val to = moveWorkerDto.to
        logger.info("Перемещение работника из $from в $to")
        buildingService.moveWorker(from, to)
    }
}