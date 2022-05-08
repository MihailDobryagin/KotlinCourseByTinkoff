package ru.tinkoff.fintech.homework.lesson6.workers.repository

import org.springframework.data.repository.CrudRepository
import ru.tinkoff.fintech.homework.lesson6.workers.entities.MoveWorkerRequest

interface MoveWorkerRequestsRepository : CrudRepository<MoveWorkerRequest, Long> {

}