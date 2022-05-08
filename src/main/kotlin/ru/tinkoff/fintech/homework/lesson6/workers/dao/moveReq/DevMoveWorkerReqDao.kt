package ru.tinkoff.fintech.homework.lesson6.workers.dao.moveReq

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.utils.MoveWorkerReqStatus
import ru.tinkoff.fintech.homework.lesson6.workers.entities.MoveWorkerRequest

@Component
@Profile("dev")
class DevMoveWorkerReqDao(
    inputRequests: Map<Long, MoveWorkerRequest> = mutableMapOf()
) : MoveWorkerReqDao {

    private val requests = inputRequests.toMutableMap()
    private var nextReqId: Long = 0

    override fun getReq(id: Long): MoveWorkerRequest? = requests[id]

    override fun addReq(newRequest: MoveWorkerRequest): Long {
        val request = newRequest.copy(id = nextReqId++)
        requests[request.id!!] = request
        return request.id
    }

    override fun updateReq(request: MoveWorkerRequest) {
        if (!requests.contains(request.id)) throw IllegalArgumentException("Нет запроса на перемещение работника с id ${request.id}")
        requests[request.id!!] = request
    }

    override fun getStatus(id: Long): MoveWorkerReqStatus? = requests[id]?.status

    override fun getWorkerId(id: Long): Long? = requests[id]?.workerId

    override fun changeStatus(id: Long, newStatus: MoveWorkerReqStatus) {
        val req = getReq(id) ?: throw IllegalArgumentException("Нет запроса на перемещение работника с id $id")
        addReq(req.copy(status = newStatus))
    }

    override fun failReq(id: Long, message: String) {
        val request = MoveWorkerRequest(id, MoveWorkerReqStatus.FAILED, message)
        updateReq(request)
    }
}