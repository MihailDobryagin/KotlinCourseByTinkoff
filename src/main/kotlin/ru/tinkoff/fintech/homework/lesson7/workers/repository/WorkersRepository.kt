package ru.tinkoff.fintech.homework.lesson7.workers.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tinkoff.fintech.homework.lesson7.workers.entities.Worker

@Repository
interface WorkersRepository : CrudRepository<Worker, Long> {
    fun findAllBy(): Iterable<Worker>
}