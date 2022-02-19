package ru.tinkoff.fintech.homework.lesson1.animals

import ru.tinkoff.fintech.homework.lesson1.House

interface CanHaveHome {
    fun getHome(): House?
    fun setHome(home: House?)
}