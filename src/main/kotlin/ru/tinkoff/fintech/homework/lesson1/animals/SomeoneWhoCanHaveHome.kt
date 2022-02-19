package ru.tinkoff.fintech.homework.lesson1.animals

import ru.tinkoff.fintech.homework.lesson1.House

interface SomeoneWhoCanHaveHome {
    fun moveIntoHouse(home: House)

    fun evict()
}