package ru.tinkoff.fintech.homework.lesson4

class MyQueue<T> : MyCollection<T>() {
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

    fun remove(): T =
        poll() ?: throw NoSuchElementException()

    fun offer(e: T): Boolean = add(e)

    fun element(): T =
        head?.value ?: throw NoSuchElementException()

    override fun add(element: T): Boolean {
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

    override fun clear() {
        head = null
        back = null
        size = 0
    }

    override fun peek(): T? = head?.value
}