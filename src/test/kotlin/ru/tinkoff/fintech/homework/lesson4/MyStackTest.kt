package ru.tinkoff.fintech.homework.lesson4

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import ru.tinkoff.fintech.homework.lesson4.utils.MyCollectionTests

class MyStackTest {

    private val stack = spyk(MyStack<String>())
    private val listOfValues = listOf("123", "", "9876")

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkPushing() {
        stack.add(listOfValues[0])
        stack.addAll(listOfValues.subList(1, listOfValues.size))

        val listFromQueue = stack.toList().reversed()

        assertAll(
            { assertEquals(listOfValues.size, stack.size) },
            { assertEquals(listOfValues, listFromQueue) },
        )
    }

    @Test
    fun checkContainsAll() = MyCollectionTests.checkContainsAll(stack, listOfValues)

    @Test
    fun checkPopFromEmptyStack() = MyCollectionTests.checkPopFromEmptyCollection(stack)

    @Test
    fun checkPopFromNotEmptyStack() {
        stack.addAll(listOfValues)
        val element = stack.pop()
        assertAll(
            { assertEquals(listOfValues.last(), element) },
            { assertEquals(listOfValues.size - 1, stack.size) }
        )
    }

    @Test
    fun checkPeekFromEmptyStack() = MyCollectionTests.checkPeekFromEmptyCollection(stack)

    @Test
    fun checkPeekFromNotEmptyStack() {
        stack.addAll(listOfValues)
        val element = stack.peek()
        assertAll(
            { assertEquals(listOfValues.last(), element) },
            { assertEquals(listOfValues.size, stack.size) }
        )
    }
}