package ru.tinkoff.fintech.homework.lesson4

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class MyStackTest {

    private val stack = spyk(MyQueue<String>())
    private val listOfValues = listOf("123", "", "1234")

    @Test
    fun checkAdding() = MyCollectionTests.checkAdding(stack, listOfValues)

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }
}