package ru.tinkoff.fintech.homework.lesson3.animals

import io.mockk.clearAllMocks
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import ru.tinkoff.fintech.homework.lesson3.House
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class HouseTest {
    private val human = spyk(Human("MainHuman", 80))
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
    fun checkInitialization() {
        verify { house.addResident(human) }
        assertAll(
            { assert(isHumanInHouse(house, human)) },
            { assertContentEquals(setOf(human), house.getResidents().asIterable()) },
        )
    }

    @Test
    fun checkSettingOwner() {
        val newHuman = spyk(Human("NewHuman", 111))
        house.owner = newHuman
        assertAll(
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

        verify { house.addPet(cat) }
        assertAll(
            { assert(house.getResidents().containsAll(listOf(cat, human))) },
            { assertContentEquals(setOf(cat).asIterable(), house.getPets()) },
        )
    }

    @Test
    fun checkEvictPet() {
        house.addPet(cat)
        house.evictPet(cat)

        verify { house.evictResident(cat) }

        assertAll(
            { assert(!house.getResidents().union(house.getPets()).contains(cat)) },
            { assertEquals(null, cat.home) },
        )
    }

    private fun isHumanInHouse(house: House, human: Human): Boolean {
        return house.getResidents().contains(human) &&
                human.home == house

    }
}