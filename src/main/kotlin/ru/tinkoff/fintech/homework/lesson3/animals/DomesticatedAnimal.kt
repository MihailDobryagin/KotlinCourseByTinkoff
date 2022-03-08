package ru.tinkoff.fintech.homework.lesson3.animals

import ru.tinkoff.fintech.homework.lesson3.House

abstract class DomesticatedAnimal(
    name: String,
    weight: Int,
    color: String?,
) : Animal(name, weight, color), SomeoneWhoCanHaveHome {
    var home: House? = null
        private set

    override fun moveIntoHouse(home: House) {
        this.home = home
    }

    override fun evict() {
        this.home = null
    }
}