package ru.tinkoff.fintech.homework.lesson6.workers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.homework.lesson6.workers.client.BuildingClient

@Service
class WorkersService @Autowired constructor(
    val buildingClient: BuildingClient,
    val workers: Map<Long, Worker>,
) {

    fun moveWorker(workerId: Long, from: Long, to: Long) {

        buildingClient.moveWorker(from, to)
    }
}