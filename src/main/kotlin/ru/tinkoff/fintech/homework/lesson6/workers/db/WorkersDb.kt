package ru.tinkoff.fintech.homework.lesson6.workers.db

import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.workers.dto.WorkerDto

@Component
class WorkersDb(
    private val workers: MutableMap<Long, Worker> = mutableMapOf()
) {
    private var nextWorkerId: Long = 0
    fun getWorkers(): Map<Long, WorkerDto> = workers.mapValues {
        WorkerDto(it.value.id, it.value.name, it.value.roomId)
    }.toMap()

    fun getWorker(workerId: Long): WorkerDto? = workers[workerId]?.let {
        WorkerDto(it.id, it.name, it.roomId)
    }

    fun addWorker(workerDto: WorkerDto): Long {
        val worker = Worker(nextWorkerId++, workerDto.name!!, workerDto.roomId)
        workers[worker.id] = worker
        return worker.id
    }

    fun updateWorker(id: Long, workerDto: WorkerDto): Boolean {
        return if (!workers.contains(id)) false
        else {
            workers[id] = Worker(id, workerDto.name!!, workerDto.roomId)
            true
        }
    }
}