package ru.tinkoff.fintech.homework.lesson3.animals

import ru.tinkoff.fintech.homework.lesson3.House
import kotlin.math.max
import kotlin.math.min

class Human(
    name: String,
    weight: Int,
) : Animal(name, weight, null), SomeoneWhoCanHaveHome {
    var mood = 10
        private set
    var home: House? = null
        private set

    fun work(hours: Int) {
        weight = max(1, weight - hours)
        mood = max(0, mood - hours / 2)
    }

    override fun voice() = println("Hello!")

    override fun getEmoji() = "('_')"

    override fun eat(foodWeight: Int) {
        super.eat(foodWeight)
        mood = min(10, mood + foodWeight)
    }

    override fun moveIntoHouse(home: House) {
        this.home = home
    }

    override fun evict() {
        this.home = null
    }
}