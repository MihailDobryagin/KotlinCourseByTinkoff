package ru.tinkoff.fintech.homework.lesson7.building.entities

import javax.persistence.*

@Entity
@Table(name = "rooms")
data class Room(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "count_of_people", nullable = false)
    val countOfPeople: Int = 0,
) {
    constructor() : this(name = "")
}
