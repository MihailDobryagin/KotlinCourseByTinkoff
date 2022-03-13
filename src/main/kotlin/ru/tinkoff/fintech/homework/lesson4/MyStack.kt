package ru.tinkoff.fintech.homework.lesson4

class MyStack<T : Any> : MyCollection<T>() {

    fun push(element: T) = add(element)

    fun pop(): T = forceRemoveHead()

    override fun add(element: T) {
        val newNode = Node(element, head)

        if (size == 0)
            back = newNode

        head = newNode
        size++
    }
}