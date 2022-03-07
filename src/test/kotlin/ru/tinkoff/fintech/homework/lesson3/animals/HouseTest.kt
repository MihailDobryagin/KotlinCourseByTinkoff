package ru.tinkoff.fintech.homework.lesson3.animals

import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import ru.tinkoff.fintech.homework.lesson3.House
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class HouseTest {
    private lateinit var human: Human
    private lateinit var cat: Cat
    private lateinit var house: House

    @BeforeEach
    fun beforeEach() {
        human = spyk(Human("MainHuman", 80))
        house = House(human)
        cat = spyk(Cat("BobCat", null))
    }

    @Test
    fun checkInitialization() {
        assertAll(
            null,
            { assert(isHumanInHouse(house, human)) },
            { assertContentEquals(setOf(human), house.getResidents().asIterable()) },
            { verify { house.addResident(human) } }
        )
    }

    @Test
    fun checkSettingOwner() {
        val newHuman = spyk(Human("NewHuman", 111))
        house.owner = newHuman
        assertAll(
            null,
            { assert(isHumanInHouse(house, human)) },
            { assert(house.getResidents().containsAll(setOf(human, newHuman))) }
        )
    }

    @Test
    fun checkAddResident() {
        val newHuman = spyk(Human("NewHuman", 123))
        house.addResident(newHuman)
        assert(isHumanInHouse(house, newHuman))
    }

    @Test
    fun checkAddPet() {
        house.addPet(cat)

        assertAll(
            null,
            { assert(house.getResidents().containsAll(listOf(cat, human))) },
            { assertContentEquals(setOf(cat).asIterable(), house.getPets()) },
            { verify { house.addPet(cat) } },
        )
    }

    @Test
    fun checkEvictPet() {
        house.addPet(cat)
        house.evictPet(cat)

        assertAll(
            null,
            { assert(!house.getResidents().union(house.getPets()).contains(cat)) },
            { assertEquals(null, cat.home) },
            { verify { house.evictResident(cat) } },
        )
    }

    private fun isHumanInHouse(house: House, human: Human): Boolean {
        return house.getResidents().contains(human) &&
                human.home == house

    }
}