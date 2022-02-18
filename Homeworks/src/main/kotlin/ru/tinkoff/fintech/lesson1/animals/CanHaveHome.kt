package ru.tinkoff.fintech.lesson1.animals

import ru.tinkoff.fintech.lesson1.House

interface CanHaveHome {
    fun getHome(): House?
    fun setHome(home: House?)
}