package ru.tinkoff.fintech.homework.lesson7.workers.dao

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson7.workers.entities.Worker
import ru.tinkoff.fintech.homework.lesson7.workers.repository.WorkersRepository

@Component
@Profile("jpa")
class JpaWorkerDao(
    val workersRepository: WorkersRepository,
) : WorkerDao {
    override fun getWorkers(): Map<Long, Worker> {
        return workersRepository.findAllBy().associateBy { it.id!! }
    }

    override fun getWorker(workerId: Long): Worker? {
        return workersRepository.findById(workerId).orElse(null)
    }

    override fun addWorker(workerForAdd: Worker): Long? {
        return workersRepository.save(workerForAdd).id
    }

    override fun updateWorker(id: Long, worker: Worker) {
        val prevRoom = getWorker(id) ?: throw IllegalArgumentException("Не сушествует работника с id = $id")
        workersRepository.save(prevRoom.copy(name = worker.name, roomId = worker.roomId))
    }
}