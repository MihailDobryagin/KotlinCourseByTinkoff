package ru.tinkoff.fintech.homework.lesson1.animals

import ru.tinkoff.fintech.homework.lesson1.House

abstract class DomesticatedAnimal(
    name: String,
    weight: Int,
    color: String?,
) : Animal(name, weight, color), CanHaveHome {
    private var home: House? = null

    override fun die() {
        home = null
        super.die()
    }

    override fun setHome(home: House?) {
        this.home = home
    }

    override fun getHome(): House? = home
}