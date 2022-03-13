package ru.tinkoff.fintech.homework.lesson4

abstract class MyCollection<T : Any> : Collection<T> {
    protected var head: Node<T>? = null

    protected var back: Node<T>? = null

    override var size: Int = 0
        protected set

    /**
     * Get element from head without removing
     */
    fun peek(): T? = head?.value

    fun clear() {
        head = null
        back = null
        size = 0
    }

    override fun contains(element: T): Boolean =
        this.find { it == element } != null

    override fun containsAll(elements: Collection<T>): Boolean =
        elements.find { !this.contains(it) } == null

    override fun isEmpty(): Boolean = size == 0

    override fun iterator(): Iterator<T> = FromHeadToBackIterator(head)

    /**
     * Remove element from head
     *
     * @return NULL if collection is empty
     */
    protected fun removeHead(): T? {
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
     * Like removeHead() but throws NoSuchElementException if collection is empty
     */
    protected fun forceRemoveHead() =
        removeHead() ?: throw NoSuchElementException()

    protected abstract fun add(element: T)

    protected class FromHeadToBackIterator<T>(
        private var curNode: Node<T>?
    ) : Iterator<T> {
        override fun hasNext(): Boolean = curNode != null

        override fun next(): T {
            if (!hasNext()) throw NoSuchElementException()
            val result = curNode!!.value
            curNode = curNode!!.next
            return result
        }
    }

    protected data class Node<T>(
        val value: T,
        var next: Node<T>?,
    )
}