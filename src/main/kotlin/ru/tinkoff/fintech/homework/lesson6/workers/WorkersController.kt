package ru.tinkoff.fintech.homework.lesson6.workers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("workers")
class WorkersController @Autowired constructor(
    private val workersService: WorkersService,
) {
    @GetMapping
    @ResponseBody
    fun get(): Map<Long, Worker> {
        return workersService.workers
    }

    @GetMapping("move")
    @ResponseBody
    fun get(
        @RequestParam workerId: Long,
        @RequestParam from: Long,
        @RequestParam to: Long
    ) {
        workersService.moveWorker(workerId, from, to)
    }
}