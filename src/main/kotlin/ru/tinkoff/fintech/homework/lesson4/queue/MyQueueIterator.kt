package ru.tinkoff.fintech.homework.lesson4.queue

import kotlin.collections.Iterator

internal class MyQueueIterator<T>(
    var curNode: Node<T>?
) : Iterator<T> {
    override fun hasNext(): Boolean = curNode != null

    override fun next(): T {
        if (!hasNext()) throw NoSuchElementException()
        val result = curNode!!.value
        curNode = curNode!!.next
        return result
    }
}
