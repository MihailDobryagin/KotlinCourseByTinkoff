package ru.tinkoff.fintech.homework.lesson4

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class MyQueueTest {

    private val queue = spyk(MyQueue<String>())
    private val listOfValues = listOf("123", "", "1234")

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkAdding() = MyCollectionTests.checkAdding(queue, listOfValues)

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
    fun checkPeekFromNotEmptyQueue() = MyCollectionTests.checkPeekFromNotEmptyCollection(queue, listOfValues)

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