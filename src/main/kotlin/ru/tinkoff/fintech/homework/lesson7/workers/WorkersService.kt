package ru.tinkoff.fintech.homework.lesson7.workers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson7.utils.ValidationException
import ru.tinkoff.fintech.homework.lesson7.workers.client.BuildingClient
import ru.tinkoff.fintech.homework.lesson7.workers.dao.DevWorkerDao
import ru.tinkoff.fintech.homework.lesson7.workers.dao.WorkerDao
import ru.tinkoff.fintech.homework.lesson7.workers.entities.Worker

@Service
class WorkersService(
    private val buildingClient: BuildingClient,
    private val devWorkerDao: WorkerDao,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersService::class.java)
    }

    fun getWorkers(): Map<Long, Worker> {
        return devWorkerDao.getWorkers()
    }

    fun getWorker(id: Long): Worker? {
        return devWorkerDao.getWorker(id)
    }

    fun addWorker(name: String): Long {
        val worker = Worker(name = name)
        return devWorkerDao.addWorker(worker) ?: throw IllegalStateException()
    }

    fun moveWorker(workerId: Long, to: Long?) {
        logger.info("Перемещение работника $workerId в комнату $to")

        val worker = getWorker(workerId) ?: throw ValidationException("Не существует работника с id $workerId")
        val from = worker.roomId
        val movingResult = buildingClient.moveWorker(from, to)

        if (!movingResult) {
            throw Exception("Не удалось переместить работника ${worker.id} из $from в $to")
        } else {
            val workerForUpdate = worker.copy(roomId = to)
            devWorkerDao.updateWorker(workerId, workerForUpdate)
        }
    }
}