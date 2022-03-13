package ru.tinkoff.fintech.homework.lesson4

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNull

class MyStackTest {

    private val stack = MyStack<String>()

    @AfterEach
    fun afterEach() {
        stack.clear()
    }

    @Test
    fun checkPushing() {
        stack.push(singleValue)
        pushAll(listOfValues)

        val valuesFromStack = stack.toList().reversed()
        val expectedValues = listOf(singleValue).plus(listOfValues)
        assertAll(
            { assertEquals(expectedValues.size, stack.size) },
            { assertEquals(expectedValues, valuesFromStack) },
        )
    }

    @Test
    fun checkContainsAll() {
        pushAll(listOfValues)

        assertTrue(stack.containsAll(listOfValues))
    }

    @Test
    fun checkPopFromEmptyStack() {
        assertThrows<NoSuchElementException> {
            stack.pop()
        }
    }

    @Test
    fun checkPopFromNotEmptyStack() {
        pushAll(listOfValues)

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
        pushAll(listOfValues)

        val element = stack.peek()

        assertAll(
            { assertEquals(listOfValues.last(), element) },
            { assertEquals(listOfValues.size, stack.size) }
        )
    }

    private fun pushAll(elements: Collection<String>) {
        elements.forEach(stack::push)
    }

    private val singleValue = "123"
    private val listOfValues = listOf("", "9876")
}