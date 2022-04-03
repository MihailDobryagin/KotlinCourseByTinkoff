package ru.tinkoff.fintech.homework.lesson6.workers.db

import org.springframework.stereotype.Component

@Component
class WorkersDb(
    private val workers: MutableMap<Long, Worker> = mutableMapOf()
) {
    private var nextWorkerId: Long = 0
    fun getWorkers(): Map<Long, Worker> = workers.toMap()

    fun getWorker(workerId: Long): Worker? = workers[workerId]

    fun addWorker(workerForAdd: Worker): Long {
        val worker = Worker(nextWorkerId, workerForAdd.name, workerForAdd.roomId)
        workers[nextWorkerId++] = worker
        return worker.id!!
    }

    fun updateWorker(id: Long, worker: Worker): Boolean {
        return if (!workers.contains(id)) false
        else {
            workers[id] = Worker(id, worker.name, worker.roomId)
            true
        }
    }
}