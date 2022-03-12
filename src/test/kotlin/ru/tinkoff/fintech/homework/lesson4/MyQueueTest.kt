package ru.tinkoff.fintech.homework.lesson4

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import ru.tinkoff.fintech.homework.lesson4.utils.MyCollectionTests

class MyQueueTest {

    private val queue = spyk(MyQueue<String>())
    private val listOfValues = listOf("123", "", "9876")

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkOffering() {
        queue.add(listOfValues[0])
        queue.addAll(listOfValues.subList(1, listOfValues.size))

        val listFromQueue = queue.toList()

        assertAll(
            { assertEquals(listOfValues.size, queue.size) },
            { assertEquals(listOfValues, listFromQueue) },
        )
    }

    @Test
    fun checkContainsAll() = MyCollectionTests.checkContainsAll(queue, listOfValues)

    @Test
    fun checkRemoveFromEmptyQueue() {
        assertThrows<NoSuchElementException>(executable = queue::remove)
    }

    @Test
    fun checkPollFromEmptyQueue() = MyCollectionTests.checkPollFromEmptyCollection(queue)

    @Test
    fun checkRemoveFromNotEmptyQueue() {
        queue.addAll(listOfValues)
        val element = queue.remove()
        assertAll(
            { assertEquals(listOfValues.first(), element) },
            { assertEquals(listOfValues.size - 1, queue.size) }
        )
    }

    @Test
    fun checkPeekFromEmptyQueue() = MyCollectionTests.checkPeekFromEmptyCollection(queue)

    @Test
    fun checkPeekFromNotEmptyQueue() {
        queue.addAll(listOfValues)
        val element = queue.peek()
        assertAll(
            { assertEquals(listOfValues.first(), element) },
            { assertEquals(listOfValues.size, queue.size) }
        )
    }
}