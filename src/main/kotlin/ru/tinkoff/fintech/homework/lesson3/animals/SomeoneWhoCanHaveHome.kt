package ru.tinkoff.fintech.homework.lesson3.animals

import ru.tinkoff.fintech.homework.lesson3.House

interface SomeoneWhoCanHaveHome {
    fun moveIntoHouse(home: House)

    fun evict()
}