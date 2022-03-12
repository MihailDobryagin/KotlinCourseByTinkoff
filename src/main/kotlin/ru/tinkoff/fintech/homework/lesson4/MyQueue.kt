package ru.tinkoff.fintech.homework.lesson4

class MyQueue<T> : MyCollection<T>() {
    fun remove() = pop()

    fun offer(e: T): Boolean = add(e)

    fun element(): T =
        head?.value ?: throw NoSuchElementException()

    public override fun poll(): T? {
        return super.poll()
    }

    override fun push(element: T) {
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