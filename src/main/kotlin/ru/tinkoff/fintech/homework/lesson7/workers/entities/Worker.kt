package ru.tinkoff.fintech.homework.lesson7.workers.entities

import javax.persistence.*

@Entity
@Table(name = "workers")
data class Worker(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "room_id")
    val roomId: Long? = null,
) {
    constructor() : this(null, "", null)
}
