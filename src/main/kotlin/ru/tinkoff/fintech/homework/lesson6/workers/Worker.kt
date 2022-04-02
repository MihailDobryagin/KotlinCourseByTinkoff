package ru.tinkoff.fintech.homework.lesson6.workers

/**
 * Класс, который мы храним в базе
 */
data class Worker(
    val id: Long,
    val name: String,
    val roomId: Long? = null,
)
