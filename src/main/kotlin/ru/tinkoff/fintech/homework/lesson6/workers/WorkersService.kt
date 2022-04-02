package ru.tinkoff.fintech.homework.lesson6.workers

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.workers.client.BuildingClient

@Service
class WorkersService @Autowired constructor(
    val buildingClient: BuildingClient,
    val workersDb: WorkersDb,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersService::class.java)
    }

    fun getWorkers(): Map<Long, Worker> {
        return workersDb.getWorkers()
    }

    fun getWorker(id: Long): Worker? {
        return workersDb.getWorker(id)
    }

    fun addWorker(
        name: String,
    ): Long {
        return workersDb.addWorker(name)
    }

    fun moveWorker(workerId: Long, to: Long?): Boolean {
        logger.info("Перемещение работника $workerId в комнату $to")

        val worker = getWorker(workerId)
        if (worker == null) {
            logger.error("Не существует работника с id $workerId")
            return false
        }

        val from = worker.roomId

        val movingResult = buildingClient.moveWorker(from, to)

        if (!movingResult) {
            logger.error("Не удалось переместить работника ${worker.id} из $from в $to")
        } else {
            worker.roomId = to
        }

        return movingResult
    }
}