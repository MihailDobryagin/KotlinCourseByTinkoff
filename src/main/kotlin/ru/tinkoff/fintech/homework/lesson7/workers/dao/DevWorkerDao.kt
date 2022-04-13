package ru.tinkoff.fintech.homework.lesson7.workers.dao

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson7.workers.entities.Worker

@Component
@Profile("dev", "test")
class DevWorkerDao(
    inputWorkers: Map<Long, Worker> = mapOf()
) : WorkerDao {
    private val workers = inputWorkers.toMutableMap()
    private var nextWorkerId: Long = 0

    override fun getWorkers(): Map<Long, Worker> = workers.toMap()

    override fun getWorker(workerId: Long): Worker? = workers[workerId]

    override fun addWorker(workerForAdd: Worker): Long {
        val worker = workerForAdd.copy(id = nextWorkerId++)
        workers[worker.id!!] = worker
        return worker.id
    }

    override fun updateWorker(id: Long, worker: Worker) {
        if (!workers.contains(id)) throw IllegalArgumentException("Нет работника с id $id")
        workers[id] = worker
    }
}