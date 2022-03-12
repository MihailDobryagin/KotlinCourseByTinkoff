package ru.tinkoff.fintech.homework.lesson4.queue

class BestQueue<T> {

    private var head: Node<T>? = null
        set(value) {
            field = value
            if (field == null) {
                back = null
                size = 0
                return
            }
            if (back == null)
                back = field
        }

    private var back: Node<T>? = null

    var size: Int = 0
        private set

    fun add(element: T): Boolean {
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

    fun addAll(elements: Collection<T>): Boolean {
        elements.forEach(this::add)
        return true
    }

    fun clear() {
        head = null
    }

    fun remove(): T =
        poll() ?: throw NoSuchElementException()

    fun isEmpty(): Boolean = size == 0

    fun offer(e: T): Boolean = add(e)

    fun poll(): T? {
        if (size == 0) return null
        val result = head!!.value
        head = head!!.next
        return result
    }

    fun element(): T =
        head?.value ?: throw NoSuchElementException()

    fun peek(): T? = head?.value
}