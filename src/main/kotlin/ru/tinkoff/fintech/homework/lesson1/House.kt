package ru.tinkoff.fintech.homework.lesson1

import ru.tinkoff.fintech.homework.lesson1.animals.CanHaveHome
import ru.tinkoff.fintech.homework.lesson1.animals.DomesticatedAnimal
import ru.tinkoff.fintech.homework.lesson1.animals.Human

class House(owner: Human) {
    private lateinit var owner: Human

    // У этих полей будут свои геттеры, т. к.
    //  1) Нужно будет возвращать List, а не MutableList
    //  2) При get из самого House нам нужен именно сам объект residents, а не его копия
    private val residents: MutableList<CanHaveHome> = mutableListOf()
    private val pets: MutableList<DomesticatedAnimal> = mutableListOf()

    init {
        setOwner(owner)
        addResident(owner)
    }

    fun getResidents() = residents.toList()
    fun getPets() = pets.toList()

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

    override fun toString(): String {
        return "Residents:\n" +
                residents.toString() + "\n\n" +
                "Pets:\n" +
                pets.toString()
    }
}