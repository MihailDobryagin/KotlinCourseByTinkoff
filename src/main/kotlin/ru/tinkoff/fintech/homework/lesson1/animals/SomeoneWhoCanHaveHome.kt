package ru.tinkoff.fintech.homework.lesson1.animals

import ru.tinkoff.fintech.homework.lesson1.House

// Я пытался сделать home и protected и его сеттеры private, но такое в интерфейсах нельзя(
// Далее я попробовал хотя бы сделать home protected, но так тоже нельзя(
// Переопределить область видимости set в потомках тоже нельзя(
// По этим причинам нельзя сделать home послностью открытым для наследников и закрытым для внешних классов
// И тут пришло гениальное решение сделать просто абстрактный класс)
// Правда сеттер остался, но он может сетить только !null. Зато у всех потомков есть доступ к home
abstract class SomeoneWhoCanHaveHome(
    name: String,
    weight: Int,
    color: String?,
) : Animal(name, weight, color) {
    var home: House? = null
        protected set

    fun moveIntoHouse(home: House) {
        this.home = home
    }

    fun evict() {
        this.home = null
    }

    override fun die() {
        home = null
        super.die()
    }
}