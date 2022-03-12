package ru.tinkoff.fintech.homework.lesson4.queue

internal class Node<T>(
    val value: T,
    var next: Node<T>?,
)