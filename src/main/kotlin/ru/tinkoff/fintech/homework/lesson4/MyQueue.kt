package ru.tinkoff.fintech.homework.lesson4

class MyQueue<T> : MyCollection<T>() {

    fun element(): T =
        peek() ?: throw NoSuchElementException()

    fun remove() = forceRemoveHead()

    fun poll() = removeHead()

    fun offer(e: T): Boolean {
        add(e)
        return true
    }

    fun offerAll(elements: Collection<T>) = addAll(elements)

    override fun add(element: T) {
        val newNode = Node(element, null)

        if (size == 0) {
            head = newNode
        } else {
            back!!.next = newNode
        }

        back = newNode
        size++
    }
}