package ru.tinkoff.fintech.homework.lesson6.workers.entities

import javax.persistence.*

@Entity
@Table(name = "workers")
data class Worker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val roomId: Long? = null,
)
