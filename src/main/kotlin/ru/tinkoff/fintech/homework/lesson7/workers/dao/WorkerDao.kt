package ru.tinkoff.fintech.homework.lesson7.workers.dao

import ru.tinkoff.fintech.homework.lesson7.workers.entities.Worker

interface WorkerDao {
    fun getWorkers(): Map<Long, Worker>

    fun getWorker(workerId: Long): Worker?

    fun addWorker(workerForAdd: Worker): Long?

    fun updateWorker(id: Long, worker: Worker)
}