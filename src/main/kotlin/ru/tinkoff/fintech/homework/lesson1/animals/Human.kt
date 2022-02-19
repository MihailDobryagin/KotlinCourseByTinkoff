package ru.tinkoff.fintech.homework.lesson1.animals

import ru.tinkoff.fintech.homework.lesson1.House
import kotlin.math.max
import kotlin.math.min

class Human(
    name: String,
    weight: Int,
) : SomeoneWhoCanHaveHome(name, weight, null) {

    var mood = 10
        private set

    override fun voice() = println("Hello!")

    override fun getEmoji() = "('_')"

    fun work(hours: Int) {
        weight = max(1, weight - hours)
        mood = max(0, mood - hours / 2)
    }

    override fun eat(foodWeight: Int) {
        super.eat(foodWeight)
        mood = min(10, mood + foodWeight)
    }
}