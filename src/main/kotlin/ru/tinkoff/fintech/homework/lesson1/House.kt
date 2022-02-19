package ru.tinkoff.fintech.homework.lesson1

import ru.tinkoff.fintech.homework.lesson1.animals.DomesticatedAnimal
import ru.tinkoff.fintech.homework.lesson1.animals.Human
import ru.tinkoff.fintech.homework.lesson1.animals.SomeoneWhoCanHaveHome

class House {
    var owner: Human
        set(value) {
            field = value
            addResident(field)
            println("$field has own house")
        }

    // Нужен такой конструктор, т. к. если принимать в самом-самом главном (который пишется прямо после названия класса), то ругается, что owner может быть не объявлен
    constructor(owner: Human) {
        this.owner = owner
    }

    // У этих полей будут свои геттеры, т. к.
    //  1) Нужно будет возвращать Set, а не MutableSet
    //  2) При get из самого House нам нужен именно сам объект residents, а не его копия
    private val residents: MutableSet<SomeoneWhoCanHaveHome> = mutableSetOf()
    private val pets: MutableSet<DomesticatedAnimal> = mutableSetOf()

    fun getResidents() = residents.toSet()
    fun getPets() = pets.toSet()

    fun addResident(resident: SomeoneWhoCanHaveHome) {
        residents.add(resident)
        resident.moveIntoHouse(this)
    }

    fun evictResident(resident: SomeoneWhoCanHaveHome) {
        resident.evict()
        residents.remove(resident)
        pets.remove(resident)
        println("$resident left home")
    }

    fun addPet(pet: DomesticatedAnimal) {
        addResident(pet)
        pets.add(pet)
        println("$pet was added as pet")
    }

    fun addPets(animals: Iterable<DomesticatedAnimal>) = animals.forEach(this::addPet)

    fun evictPet(pet: DomesticatedAnimal) = evictResident(pet)

    override fun toString(): String {
        return "Residents:\n" +
                residents.toString() + "\n\n" +
                "Pets:\n" +
                pets.toString()
    }
}