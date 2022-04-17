package ru.tinkoff.fintech.homework.lesson6.workers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import ru.tinkoff.fintech.homework.lesson6.workers.entities.Worker

@RestController
@RequestMapping("workers")
class WorkersController(
    private val workersService: WorkersService,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersController::class.java)
    }

    @GetMapping
    fun get(): List<Worker> {
        logger.info("Запрос на получение работников")
        return workersService.getWorkers()
    }

    @GetMapping("{id}")
    fun getById(
        @PathVariable id: Long,
    ): Worker? {
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
    ) {
        logger.info("Запрос на перемещение работника $workerId в помещение $to")
        workersService.moveWorker(workerId, to)
    }
}