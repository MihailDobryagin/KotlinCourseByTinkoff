package ru.tinkoff.fintech.homework.lesson6.workers.dao.moveReq

import ru.tinkoff.fintech.homework.lesson6.utils.MoveWorkerReqStatus
import ru.tinkoff.fintech.homework.lesson6.workers.entities.MoveWorkerRequest

interface MoveWorkerReqDao {
    fun getReq(id: Long): MoveWorkerRequest?

    fun addReq(newRequest: MoveWorkerRequest): Long?

    fun updateReq(request: MoveWorkerRequest)

    fun getStatus(id: Long): MoveWorkerReqStatus?

    fun changeStatus(id: Long, newStatus: MoveWorkerReqStatus)

    fun getWorkerId(id: Long): Long?

    fun changeToFailed(id: Long, message: String = "")
}