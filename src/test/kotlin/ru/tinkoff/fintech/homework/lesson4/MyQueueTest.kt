package ru.tinkoff.fintech.homework.lesson4

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNull

class MyQueueTest {

    private val queue = spyk(MyQueue<String>())
    private val listOfValues = listOf("123", "", "1234")

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkAdding() {
        queue.add("123")
        queue.addAll(listOfValues.subList(1, listOfValues.size))

        val listFromQueue = queue.toList()

        assertAll(
            { assertEquals(listOfValues.size, queue.size) },
            { assertEquals(listOfValues, listFromQueue) },
        )
    }

    @Test
    fun checkContainsAll() {
        queue.addAll(listOfValues)
        assertTrue(queue.containsAll(listOfValues.toList()))
    }

    @Test
    fun checkRemoveFromEmptyQueue() {
        assertThrows<NoSuchElementException>(executable = queue::remove)
    }

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
    fun checkPeekFromEmptyQueue() {
        val element = queue.peek()
        assertNull(element)
    }

    @Test
    fun checkPeekFromNotEmptyQueue() {
        queue.addAll(listOfValues)
        val element = queue.peek()
        assertAll(
            { assertEquals(listOfValues.first(), element) },
            { assertEquals(listOfValues.size, queue.size) }
        )
    }

//    @ParameterizedTest
//    @MethodSource("listOfValues")
//
//    companion object {
//        @JvmStatic
//        fun listOfValues() = listOf(
//            Arguments.of("123"),
//            Arguments.of(""),
//            Arguments.of("abcd"),
//            Arguments.of("qkjbbgk2_"),
//        )
//    }

}