package ru.tinkoff.fintech.homework.lesson4

abstract class MyCollection<T> : MutableCollection<T> {

    protected var head: Node<T>? = null

    protected var back: Node<T>? = null

    override var size: Int = 0
        protected set

    override fun addAll(elements: Collection<T>): Boolean {
        elements.forEach(this::add)
        return true
    }

    override fun containsAll(elements: Collection<T>): Boolean =
        elements.find { !this.contains(it) } == null

    override fun isEmpty(): Boolean = size == 0

    override fun iterator(): MutableIterator<T> = Iterator(head)

    abstract fun peek(): T?

    protected class Iterator<T>(
        private var curNode: Node<T>?
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

    protected data class Node<T>(
        val value: T,
        var next: Node<T>?,
    )
}