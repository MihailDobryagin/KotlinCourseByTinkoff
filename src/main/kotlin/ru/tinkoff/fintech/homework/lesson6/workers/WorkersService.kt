package ru.tinkoff.fintech.homework.lesson6.workers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.utils.MoveWorkerReqStatus
import ru.tinkoff.fintech.homework.lesson6.utils.ValidationException
import ru.tinkoff.fintech.homework.lesson6.workers.client.BuildingClient
import ru.tinkoff.fintech.homework.lesson6.workers.dao.worker.WorkerDao
import ru.tinkoff.fintech.homework.lesson6.workers.entities.MoveWorkerRequest
import ru.tinkoff.fintech.homework.lesson6.workers.entities.Worker

@Service
class WorkersService(
    private val buildingClient: BuildingClient,
    private val workerDao: WorkerDao,
    private val moveWorkerRequestsService: MoveWorkerRequestsService,
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

    fun addWorker(name: String): Long = runBlocking(Dispatchers.IO) {
        val worker = Worker(name = name)
        // TODO ("Переменная пропадает")
        val reqId = moveWorkerRequestsService.add(newRequest = MoveWorkerRequest())
            ?: throw IllegalStateException("Ошибка при запросе на добавление работника")
        launch {
            moveWorkerRequestsService.changeStatus(reqId, MoveWorkerReqStatus.IN_PROCESS)
            val workerId = workerDao.addWorker(worker)
            if(workerId == null) {
                moveWorkerRequestsService.changeStatusToFailed(reqId)
            }
            else {
                moveWorkerRequestsService.update(MoveWorkerRequest(
                    id = reqId,
                    status = MoveWorkerReqStatus.SUCCESS,
                    workerId = workerId
                ))
            }
        }
        return@runBlocking reqId
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