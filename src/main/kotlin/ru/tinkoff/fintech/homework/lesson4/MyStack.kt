package ru.tinkoff.fintech.homework.lesson4

class MyStack<T> : MyCollection<T>() {

    fun push(element: T) = add(element)

    fun pop() = forceRemoveHead()

    fun pushAll(elements: Collection<T>) = addAll(elements)

    override fun add(element: T) {
        val newNode = Node(element, head)

        if (size == 0)
            back = newNode

        head = newNode
        size++
    }
}