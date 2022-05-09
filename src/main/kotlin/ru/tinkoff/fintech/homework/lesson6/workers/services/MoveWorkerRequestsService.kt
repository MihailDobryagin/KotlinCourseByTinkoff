package ru.tinkoff.fintech.homework.lesson6.workers.services

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.utils.MoveWorkerReqStatus
import ru.tinkoff.fintech.homework.lesson6.workers.dao.moveReq.MoveWorkerReqDao
import ru.tinkoff.fintech.homework.lesson6.workers.entities.MoveWorkerRequest

@Service
class MoveWorkerRequestsService(
    private val moveWorkerReqDao: MoveWorkerReqDao
) {
    companion object {
        private val logger = LoggerFactory.getLogger(MoveWorkerRequestsService::class.java)
    }

    fun get(id: Long): MoveWorkerRequest? {
        logger.info("Запрос на получение информации по запросу $id")
        return moveWorkerReqDao.getReq(id)
    }

    fun add(newRequest: MoveWorkerRequest): Long? {
        return moveWorkerReqDao.addReq(newRequest)
    }

    fun update(request: MoveWorkerRequest) {
        moveWorkerReqDao.updateReq(request)
    }

    fun getStatus(id: Long): MoveWorkerReqStatus? {
        logger.info("Запрос на получение статуса у запроса $id")
        return moveWorkerReqDao.getStatus(id)
    }

    fun getWorkerId(id: Long): Long? {
        logger.info("Запрос на получение id работника по reqId=$id")
        return moveWorkerReqDao.getWorkerId(id)
    }

    fun changeStatus(id: Long, newStatus: MoveWorkerReqStatus) {
        logger.info("Статус запроса $id меняется на $newStatus")
        moveWorkerReqDao.changeStatus(id, newStatus)
    }

    fun changeStatusToFailed(id: Long, message: String = "") {
        logger.warn("Запрос $id прошёл не успешно")
        moveWorkerReqDao.changeToFailed(id, message)
    }
}