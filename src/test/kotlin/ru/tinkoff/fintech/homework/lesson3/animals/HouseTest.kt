package ru.tinkoff.fintech.homework.lesson3.animals

import io.mockk.clearAllMocks
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import ru.tinkoff.fintech.homework.lesson3.House

class HouseTest {
    private val human = spyk(Human("MainHuman", 80))
    private val newHuman = spyk(Human("NewHuman", 123))
    private val cat = spyk(Cat("BobCat", "white"))
    private lateinit var house: House

    @BeforeEach
    fun beforeEach() {
        house = House(human)
    }

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkAddResident() {
        house.addResident(newHuman)

        verify { newHuman.moveIntoHouse(house) }
        assertTrue(isHumanInHouse(house, newHuman))
    }

    @Test
    fun checkSettingOwner() {
        house.owner = newHuman

        verify { newHuman.moveIntoHouse(house) }
        assertAll(
            { assertTrue(isHumanInHouse(house, human)) },
            { assertTrue(isHumanInHouse(house, newHuman)) }
        )
    }

    @Test
    fun checkAddPet() {
        house.addPet(cat)

        assertAll(
            { assertTrue(house.getResidents().containsAll(listOf(cat, human))) },
            { assertTrue(house.getPets() == setOf(cat)) },
        )
    }

    @Test
    fun checkEvictPet() {
        house.addPet(cat)
        house.evictPet(cat)

        verify { cat.evict() }
        assertAll(
            { assertFalse(house.getResidents().contains(cat)) },
            { assertFalse(house.getPets().contains(cat)) },
            { assertNull(cat.home) },
        )
    }

    private fun isHumanInHouse(house: House, human: Human): Boolean {
        return house.getResidents().contains(human) &&
                human.home == house
    }
}