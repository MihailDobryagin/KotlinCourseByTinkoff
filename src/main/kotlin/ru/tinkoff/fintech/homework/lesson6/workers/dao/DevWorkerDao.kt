package ru.tinkoff.fintech.homework.lesson6.workers.dao

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.workers.entities.Worker

@Component
@Profile("dev", "test")
class DevWorkerDao(
    inputWorkers: Map<Long, Worker> = mapOf()
) : WorkerDao {
    private val workers = inputWorkers.toMutableMap()
    private var nextWorkerId: Long = 0

    override fun getWorkers(): List<Worker> = workers.values.toList()

    override fun getWorker(workerId: Long): Worker? = workers[workerId]

    override fun addWorker(newWorker: Worker): Long {
        val worker = newWorker.copy(id = nextWorkerId++)
        workers[worker.id!!] = worker
        return worker.id
    }

    override fun updateWorker(worker: Worker) {
        if (!workers.contains(worker.id)) throw IllegalArgumentException("Нет работника с id ${worker.id}")
        workers[worker.id!!] = worker
    }
}