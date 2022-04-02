package ru.tinkoff.fintech.homework.lesson6.workers

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.workers.client.BuildingClient

@Service
class WorkersService @Autowired constructor(
    val buildingClient: BuildingClient,
    val workers: MutableMap<Long, Worker>,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersService::class.java)
    }

    private var nextWorkerId: Long = 0

    fun getWorker(id: Long): Worker? {
        return workers[id]
    }

    fun addWorker(
        name: String,
    ): Long {
        val worker = Worker(nextWorkerId++, name)
        logger.info("Добавлен работник $worker")
        workers[worker.id] = worker
        return worker.id
    }

    fun moveWorker(workerId: Long, to: Long?): Boolean {
        logger.info("Перемещение работника $workerId в комнату $to")
        if (!workers.contains(workerId)) {
            logger.error("Не существует работника с id $workerId")
            return false
        }
        val worker: Worker = workers[workerId]!!

        val from = worker.roomId

        if (from == to) {
            logger.error("Нельзя переместить работника из $to в $to")
            return false
        }

        val movingResult = buildingClient.moveWorker(from, to)

        if (!movingResult) {
            logger.error("Не удалось переместить работника ${worker.id} из $from в $to")
        } else {
            worker.roomId = to
        }

        return movingResult
    }
}