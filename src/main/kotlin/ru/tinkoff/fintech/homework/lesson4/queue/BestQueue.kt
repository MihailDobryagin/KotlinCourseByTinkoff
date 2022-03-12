package ru.tinkoff.fintech.homework.lesson4.queue

import java.util.*

class BestQueue<T> : Queue<T> {

    private var head: Node<T>? = null
        set(value) {
            field = value
            if (field == null) {
                back = null
                return
            }
            if (back == null)
                back = field
        }

    private var back: Node<T>? = null

    override var size: Int = 0

    override fun add(element: T): Boolean {
        if (size == 0) {
            head = Node(element, null)
            size++
            return true
        }

        val newNode = Node(element, back)
        back!!.next = newNode
        back = newNode
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        elements.forEach(this::add)
        return true
    }

    override fun clear() {
        head = null
        size = 0
    }

    override fun iterator(): MutableIterator<T> = Iterator(head)

    override fun remove(): T =
        poll() ?: throw NoSuchElementException()

    override fun contains(element: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun remove(element: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        TODO("Not yet implemented")
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        TODO("Not yet implemented")
    }

    override fun offer(e: T): Boolean = add(e)

    override fun poll(): T? {
        if (size == 0) return null
        val result = head!!.value
        head = head!!.next
        return result
    }

    override fun element(): T =
        head?.value ?: throw NoSuchElementException()

    override fun peek(): T? = head?.value
}