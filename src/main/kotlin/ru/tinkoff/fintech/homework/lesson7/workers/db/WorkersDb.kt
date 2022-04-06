package ru.tinkoff.fintech.homework.lesson7.workers.db

import org.springframework.stereotype.Component

@Component
class WorkersDb(
    inputWorkers: Map<Long, Worker> = mapOf()
) {
    private val workers = inputWorkers.toMutableMap()
    private var nextWorkerId: Long = 0

    fun getWorkers(): Map<Long, Worker> = workers.toMap()

    fun getWorker(workerId: Long): Worker? = workers[workerId]

    fun addWorker(workerForAdd: Worker): Long {
        val worker = workerForAdd.copy(id = nextWorkerId++)
        workers[worker.id!!] = worker
        return worker.id
    }

    fun updateWorker(id: Long, worker: Worker) {
        if (!workers.contains(id)) throw IllegalArgumentException("Нет работника с id $id")
        workers[id] = worker
    }
}