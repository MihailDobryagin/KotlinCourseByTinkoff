package ru.tinkoff.fintech.lesson1

import ru.tinkoff.fintech.lesson1.animals.CanHaveHome
import ru.tinkoff.fintech.lesson1.animals.DomesticatedAnimal
import ru.tinkoff.fintech.lesson1.animals.Human

class House(owner: Human) {
    private lateinit var owner: Human

    private val residents: MutableList<CanHaveHome> = mutableListOf()
    private var pets: MutableList<DomesticatedAnimal> = mutableListOf()

    init {
        setOwner(owner)
        addResident(owner)
    }

    fun addResident(resident: CanHaveHome) {
        residents.add(resident)
        resident.setHome(this)
    }

    fun addPet(pet: DomesticatedAnimal) {
        addResident(pet)
        pets.add(pet)
        println("$pet was added as pet")
    }

    fun addPets(animals: Iterable<DomesticatedAnimal>) = animals.forEach(this::addPet)

    fun deletePet(pet: DomesticatedAnimal) {
        val predicate = { el: CanHaveHome -> el is DomesticatedAnimal && el.name == pet.name }
        pets.removeIf(predicate)
        residents.removeIf(predicate)
        println("$pet left home")
    }

    fun getOwner() = owner
    fun setOwner(owner: Human) {
        this.owner = owner
        println("$owner has own house")
    }

    fun getResidents() = residents
    fun getPets() = pets

    override fun toString(): String {
        return "Residents:\n" +
                residents.toString() + "\n\n" +
                "Pets:\n" +
                pets.toString()
    }
}