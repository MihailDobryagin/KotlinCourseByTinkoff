package ru.tinkoff.fintech.homework.lesson6.workers

import org.springframework.stereotype.Component

@Component
class WorkersDb(
    private val workers: MutableMap<Long, Worker> = mutableMapOf()
) {
    private var nextWorkerId: Long = 0
    fun getWorkers(): Map<Long, Worker> = workers.toMap()
    fun getWorker(workerId: Long): Worker? = workers[workerId]

    fun addWorker(name: String): Long {
        val worker = Worker(nextWorkerId++, name, null)
        workers[worker.id] = worker
        return worker.id
    }
}