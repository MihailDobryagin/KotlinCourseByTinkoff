package ru.tinkoff.fintech.homework.lesson6.workers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import ru.tinkoff.fintech.homework.lesson6.workers.dto.WorkerDto

@RestController
@RequestMapping("workers")
class WorkersController(
    private val workersService: WorkersService,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersController::class.java)
    }

    @GetMapping
    fun get(): Map<Long, WorkerDto> {
        logger.info("Запрос на получение работников")
        return workersService.getWorkers()
    }

    @GetMapping("{id}")
    fun getById(
        @PathVariable id: Long,
    ): WorkerDto? {
        logger.info("Запрос на получение работника $id")
        return workersService.getWorker(id)
    }

    @PostMapping("add")
    fun add(@RequestParam name: String): Long {
        logger.info("Запрос на добавление работника $name")
        return workersService.addWorker(name)
    }

    @PostMapping("move/{workerId}")
    fun moveWorker(
        @PathVariable workerId: Long,
        @RequestParam to: Long?,
    ): Boolean {
        logger.info("Запрос на перемещение работника $workerId в помещение $to")
        return workersService.moveWorker(workerId, to)
    }
}