package ru.tinkoff.fintech.homework.lesson4.queue

import ru.tinkoff.fintech.homework.lesson4.Iterator
import ru.tinkoff.fintech.homework.lesson4.Node


class MyQueue<T> : Collection<T> {

    private var head: Node<T>? = null

    private var back: Node<T>? = null

    override var size: Int = 0
        private set

    fun add(element: T): Boolean {
        val newNode = Node(element, null)

        if (size == 0) {
            head = newNode
        } else {
            back!!.next = newNode
        }

        back = newNode
        size++
        return true
    }

    fun addAll(elements: Collection<T>): Boolean {
        elements.forEach(this::add)
        return true
    }

    fun clear() {
        head = null
        back = null
        size = 0
    }

    fun remove(): T =
        poll() ?: throw NoSuchElementException()

    override fun isEmpty(): Boolean = size == 0

    override fun contains(element: T): Boolean =
        this.find { it == element } != null

    override fun containsAll(elements: Collection<T>): Boolean =
        elements.find { !this.contains(it) } == null


    override fun iterator(): kotlin.collections.Iterator<T> = Iterator(head)

    fun offer(e: T): Boolean = add(e)

    fun poll(): T? {
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

    fun element(): T =
        head?.value ?: throw NoSuchElementException()

    fun peek(): T? = head?.value
}