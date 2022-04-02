package ru.tinkoff.fintech.homework.lesson6.workers

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.workers.client.BuildingClient
import ru.tinkoff.fintech.homework.lesson6.workers.db.WorkersDb
import ru.tinkoff.fintech.homework.lesson6.workers.dto.WorkerDto

@Service
class WorkersService @Autowired constructor(
    val buildingClient: BuildingClient,
    val workersDb: WorkersDb,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersService::class.java)
    }

    fun getWorkers(): Map<Long, WorkerDto> {
        return workersDb.getWorkers()
    }

    fun getWorker(id: Long): WorkerDto? {
        return workersDb.getWorker(id)
    }

    fun addWorker(
        name: String,
    ): Long {
        val workerDto = WorkerDto(name = name)
        return workersDb.addWorker(workerDto)
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
            workersDb.updateWorker(workerId, worker)
        }

        return movingResult
    }
}