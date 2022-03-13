package ru.tinkoff.fintech.homework.lesson4

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertNull

class MyStackTest {

    private val stack = MyStack<String>()
    private val lonelyValue = "123"
    private val listOfValues = listOf("", "9876")

    @AfterEach
    fun afterEach() {
        stack.clear()
    }

    @Test
    fun checkPushing() {
        stack.push(lonelyValue)
        stack.pushAll(listOfValues)

        val valuesFromStack = stack.toList().reversed()
        val expectedValues = listOf(lonelyValue).plus(listOfValues)

        assertAll(
            { assertEquals(expectedValues.size, stack.size) },
            { assertEquals(expectedValues, valuesFromStack) },
        )
    }

    @Test
    fun checkContainsAll() {
        stack.pushAll(listOfValues)
        Assertions.assertTrue(stack.containsAll(listOfValues))
    }

    @Test
    fun checkPopFromEmptyStack() {
        assertThrows<NoSuchElementException>(stack::pop)
    }

    @Test
    fun checkPopFromNotEmptyStack() {
        stack.pushAll(listOfValues)
        val element = stack.pop()
        assertAll(
            { assertEquals(listOfValues.last(), element) },
            { assertEquals(listOfValues.size - 1, stack.size) }
        )
    }

    @Test
    fun checkPeekFromEmptyStack() {
        val element = stack.peek()
        assertNull(element)
    }

    @Test
    fun checkPeekFromNotEmptyStack() {
        stack.pushAll(listOfValues)
        val element = stack.peek()
        assertAll(
            { assertEquals(listOfValues.last(), element) },
            { assertEquals(listOfValues.size, stack.size) }
        )
    }
}