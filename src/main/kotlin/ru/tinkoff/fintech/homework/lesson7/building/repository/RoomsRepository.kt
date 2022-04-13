package ru.tinkoff.fintech.homework.lesson7.building.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tinkoff.fintech.homework.lesson7.building.entities.Room

@Repository
interface RoomsRepository : CrudRepository<Room, Long> {
    fun findAllBy(): Iterable<Room>
}