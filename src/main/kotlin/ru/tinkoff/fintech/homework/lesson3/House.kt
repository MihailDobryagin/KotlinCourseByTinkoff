package ru.tinkoff.fintech.homework.lesson3

import ru.tinkoff.fintech.homework.lesson3.animals.DomesticatedAnimal
import ru.tinkoff.fintech.homework.lesson3.animals.Human
import ru.tinkoff.fintech.homework.lesson3.animals.SomeoneWhoCanHaveHome

class House {
    var owner: Human
        set(value) {
            field = value
            addResident(field)
            println("$field has own house")
        }

    constructor(owner: Human) {
        this.owner = owner
    }

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

    fun addPets(pets: Iterable<DomesticatedAnimal>) = pets.forEach(this::addPet)

    fun evictPet(pet: DomesticatedAnimal) = evictResident(pet)

    override fun toString(): String {
        return "Residents:\n$residents\n\nPets:\n$pets"
    }
}