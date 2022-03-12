package ru.tinkoff.fintech.homework.lesson4

class MyStack<T> : MyCollection<T>() {
    override fun push(element: T) {
        val newNode = Node(element, head)

        if (size == 0)
            back = newNode

        head = newNode
        size++
    }
}