package ru.tinkoff.fintech.homework.lesson6.workers.dao.worker

import ru.tinkoff.fintech.homework.lesson6.workers.entities.Worker

interface WorkerDao {
    fun getWorkers(): List<Worker>

    fun getWorker(workerId: Long): Worker?

    fun addWorker(newWorker: Worker): Long?

    fun updateWorker(worker: Worker)
}