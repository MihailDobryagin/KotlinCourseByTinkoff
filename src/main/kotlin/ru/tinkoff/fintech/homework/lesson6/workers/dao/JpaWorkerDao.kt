package ru.tinkoff.fintech.homework.lesson6.workers.dao

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.workers.entities.Worker
import ru.tinkoff.fintech.homework.lesson6.workers.repository.WorkersRepository

@Component
@Profile("jpa")
class JpaWorkerDao(
    private val workersRepository: WorkersRepository,
) : WorkerDao {
    override fun getWorkers(): Map<Long, Worker> {
        return workersRepository.findAll().associateBy { it.id!! }
    }

    override fun getWorker(workerId: Long): Worker? {
        return workersRepository.findById(workerId).orElse(null)
    }

    override fun addWorker(newWorker: Worker): Long? {
        return workersRepository.save(newWorker).id
    }

    override fun updateWorker(worker: Worker) {
        workersRepository.save(worker)
    }
}