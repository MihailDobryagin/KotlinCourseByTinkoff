package ru.tinkoff.fintech.homework.lesson4.queue

internal class Iterator<T>(
    var curNode: Node<T>?
) : MutableIterator<T> {
    override fun hasNext(): Boolean = curNode != null

    override fun next(): T {
        if (!hasNext()) throw NoSuchElementException()
        val result = curNode!!.value
        curNode = curNode!!.next
        return result
    }

    override fun remove() {
        throw UnsupportedOperationException()
    }
}