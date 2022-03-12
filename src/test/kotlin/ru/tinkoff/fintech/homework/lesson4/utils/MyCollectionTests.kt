package ru.tinkoff.fintech.homework.lesson4.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import ru.tinkoff.fintech.homework.lesson4.MyCollection
import kotlin.test.assertNull

class MyCollectionTests {
    companion object {
        fun <T> checkContainsAll(collection: MyCollection<T>, listOfValues: List<T>) {
            collection.addAll(listOfValues)
            Assertions.assertTrue(collection.containsAll(listOfValues.toList()))
        }

        fun checkPopFromEmptyCollection(collection: MyCollection<*>) {
            assertThrows<NoSuchElementException>(executable = collection::pop)
        }

        fun checkPollFromEmptyCollection(collection: MyCollection<*>) {
            assertNull(collection.poll())
        }

        fun checkPeekFromEmptyCollection(collection: MyCollection<*>) {
            val element = collection.peek()
            assertNull(element)
        }
    }
}