package ru.tinkoff.fintech.homework.lesson6.workers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.utils.ValidationException
import ru.tinkoff.fintech.homework.lesson6.workers.client.BuildingClient
import ru.tinkoff.fintech.homework.lesson6.workers.dao.worker.WorkerDao
import ru.tinkoff.fintech.homework.lesson6.workers.entities.Worker

@Service
class WorkersService(
    private val buildingClient: BuildingClient,
    private val workerDao: WorkerDao,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersService::class.java)
    }

    fun getWorkers(): List<Worker> {
        return workerDao.getWorkers()
    }

    fun getWorker(id: Long): Worker? {
        return workerDao.getWorker(id)
    }

    fun addWorker(name: String): Long {
        val worker = Worker(name = name)
        return workerDao.addWorker(worker) ?: throw IllegalStateException("Ошибка при добавлении работника")
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
            workerDao.updateWorker(workerForUpdate)
        }
    }
}