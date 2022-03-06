package ru.tinkoff.fintech.homework.lesson3.animals

import kotlin.math.max

abstract class Animal(
    val name: String?,
    var weight: Int,
    color: String?,
) {
    val color: String = color ?: "???"

    var alive = true
        private set

    abstract fun getEmoji(): String

    open fun voice() = println("...")

    open fun eat(foodWeight: Int) {
        weight += foodWeight
    }

    open fun die() {
        alive = false
        println((name ?: getEmoji()) + " died")
        println("(´;︵;`)\n\n")
    }

    fun run() {
        weight = max(1, weight / 2)
    }

    override fun toString(): String {
        return (if (name != null) "$name  --  " else "") + getEmoji()
    }
}