package ru.tinkoff.fintech.homework.lesson4

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNull

class MyQueueTest {

    private val queue = MyQueue<String>()

    @AfterEach
    fun afterEach() {
        queue.clear()
    }

    @Test
    fun checkOffering() {
        queue.offer(lonelyValue)
        queue.offerAll(listOfValues)

        val valuesFromQueue = queue.toList()
        val expectedValues = listOf(lonelyValue).plus(listOfValues)
        assertAll(
            { assertEquals(expectedValues.size, queue.size) },
            { assertEquals(expectedValues, valuesFromQueue) },
        )
    }

    @Test
    fun checkContainsAll() {
        queue.offerAll(listOfValues)

        assertTrue(queue.containsAll(listOfValues))
    }

    @Test
    fun checkRemoveFromEmptyQueue() {
        assertThrows<NoSuchElementException> {
            queue.remove()
        }
    }

    @Test
    fun checkPollFromEmptyQueue() {
        assertNull(queue.poll())
    }

    @Test
    fun checkRemoveFromNotEmptyQueue() {
        queue.offerAll(listOfValues)

        val element = queue.remove()

        assertAll(
            { assertEquals(listOfValues.first(), element) },
            { assertEquals(listOfValues.size - 1, queue.size) }
        )
    }

    @Test
    fun checkPeekFromEmptyQueue() {
        val element = queue.peek()

        assertNull(element)
    }

    @Test
    fun checkElementMethodFromEmptyQueue() {
        assertThrows<NoSuchElementException> {
            queue.element()
        }
    }

    @Test
    fun checkPeekFromNotEmptyQueue() {
        queue.offerAll(listOfValues)

        val element = queue.peek()

        assertAll(
            { assertEquals(listOfValues.first(), element) },
            { assertEquals(listOfValues.size, queue.size) }
        )
    }

    private val lonelyValue = "123"
    private val listOfValues = listOf("", "9876")
}