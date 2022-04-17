package ru.tinkoff.fintech.homework.lesson6.building.entities

import javax.persistence.*

@Entity
@Table(name = "rooms")
data class Room(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String,
    val countOfPeople: Int = 0,
)