package ru.tinkoff.fintech.homework.lesson4

abstract class MyCollection<T> : MutableCollection<T> {
    protected var head: Node<T>? = null

    protected var back: Node<T>? = null

    /**
     * Get element from head without removing
     */
    fun peek(): T? = head?.value

    /**
     * Remove element from head
     *
     * @return NULL if collection is empty
     */
    protected open fun poll(): T? {
        if (size == 0) return null
        val result = head!!.value
        size--
        if (size == 0) {
            head = null
            back = null
        } else {
            head = head!!.next
        }
        return result
    }

    /**
     * Like poll() but throws NoSuchElementException if collection is empty
     */
    fun pop() =
        poll() ?: throw NoSuchElementException()

    /**
     * Like add but without return, because return value is always TRUE
     */
    protected abstract fun push(element: T)

    override fun add(element: T): Boolean {
        push(element)
        return true
    }

    override var size: Int = 0
        protected set

    override fun addAll(elements: Collection<T>): Boolean {
        elements.forEach(this::add)
        return true
    }

    override fun clear() {
        head = null
        back = null
        size = 0
    }

    override fun contains(element: T): Boolean =
        this.find { it == element } != null

    override fun containsAll(elements: Collection<T>): Boolean =
        elements.find { !this.contains(it) } == null

    override fun isEmpty(): Boolean = size == 0

    override fun iterator(): MutableIterator<T> = FromHeadToBackIterator(head)

    protected class FromHeadToBackIterator<T>(
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

    override fun remove(element: T): Boolean {
        throw UnsupportedOperationException()
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }
}