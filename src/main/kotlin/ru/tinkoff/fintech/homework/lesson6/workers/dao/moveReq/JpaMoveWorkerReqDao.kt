package ru.tinkoff.fintech.homework.lesson6.workers.dao.moveReq

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.utils.MoveWorkerReqStatus
import ru.tinkoff.fintech.homework.lesson6.workers.entities.MoveWorkerRequest
import ru.tinkoff.fintech.homework.lesson6.workers.repository.MoveWorkerRequestsRepository

@Component
@Profile("jpa")
class JpaMoveWorkerReqDao(
    private val moveWorkerRequestsRepository: MoveWorkerRequestsRepository,
) : MoveWorkerReqDao {
    override fun getReq(id: Long): MoveWorkerRequest? {
        return moveWorkerRequestsRepository.findById(id).orElse(null)
    }

    override fun addReq(newRequest: MoveWorkerRequest): Long? {
        return moveWorkerRequestsRepository.save(newRequest).id
    }

    override fun updateReq(request: MoveWorkerRequest) {
        moveWorkerRequestsRepository.save(request)
    }

    override fun getStatus(id: Long): MoveWorkerReqStatus? {
        return moveWorkerRequestsRepository.findById(id).orElse(null)?.status
    }

    override fun changeStatus(id: Long, newStatus: MoveWorkerReqStatus) {
        val actualReq = getReq(id) ?: throw IllegalArgumentException("Нет запроса на перемещение работника с id $id")
        updateReq(actualReq.copy(status = newStatus))
    }

    override fun getWorkerId(id: Long): Long? {
        return moveWorkerRequestsRepository.findById(id).orElse(null)?.workerId
    }

    override fun failReq(id: Long, message: String) {
        val actualReq = getReq(id) ?: throw IllegalArgumentException("Нет запроса на перемещение работника с id $id")
        updateReq(actualReq.copy(status = MoveWorkerReqStatus.FAILED, message = message))
    }
}