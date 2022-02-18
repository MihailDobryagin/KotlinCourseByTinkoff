package ru.tinkoff.fintech.lesson1.animals

import kotlin.math.max

abstract class Animal(
    open val name: String?,
    open var weight: Int,
    private val color: String? = null,
) {
    private var alive = true

    open fun voice() = println("...")

    abstract fun getEmoji(): String

    fun run() {
        weight = max(1, weight / 2)
    }

    fun getColor(): String {
        return color ?: "???"
    }

    open fun eat(foodWeight: Int) {
        weight += foodWeight
    }

    fun isAlive() = alive
    open fun die() {
        alive = false
        println((name ?: getEmoji()) + " was die")
        println("(´;︵;`)\n\n")
    }

    override fun toString(): String {
        return (if(name!=null) "$name  --  " else "") + getEmoji()
    }
}