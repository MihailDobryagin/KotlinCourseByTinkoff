package ru.tinkoff.fintech.homework.lesson4

class MyQueue<T : Any> : MyCollection<T>() {

    fun element(): T =
        peek() ?: throw NoSuchElementException()

    fun remove(): T = forceRemoveHead()

    fun poll(): T? = removeHead()

    fun offer(e: T): Boolean {
        add(e)
        return true
    }

    public override fun add(element: T) {
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