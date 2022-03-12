package ru.tinkoff.fintech.homework.lesson4

import ru.tinkoff.fintech.homework.lesson4.queue.MyQueue

fun main() {
    val queue = MyQueue<String>()

    queue.add("123")
    queue.addAll(listOf("321", "456", "1"))

    println(queue.containsAll(listOf("123", "1", "321")))
}