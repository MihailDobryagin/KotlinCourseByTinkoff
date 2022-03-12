package ru.tinkoff.fintech.homework.lesson4

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNull

class MyCollectionTests {
    companion object {
        fun <T> checkAdding(collection: MyCollection<T>, listOfValues: List<T>) {
            collection.add(listOfValues[0])
            collection.addAll(listOfValues.subList(1, listOfValues.size))

            val listFromQueue = collection.toList()

            assertAll(
                { Assertions.assertEquals(listOfValues.size, collection.size) },
                { Assertions.assertEquals(listOfValues, listFromQueue) },
            )
        }

        fun <T> checkContainsAll(collection: MyCollection<T>, listOfValues: List<T>) {
            collection.addAll(listOfValues)
            Assertions.assertTrue(collection.containsAll(listOfValues.toList()))
        }

        fun checkPopFromEmptyQueue(collection: MyCollection<*>) {
            assertThrows<NoSuchElementException>(executable = collection::pop)
        }

        fun checkPollFromEmptyCollection(collection: MyCollection<*>) {
            assertNull(collection.poll())
        }

        fun <T> checkPopFromNotEmptyQueue(collection: MyCollection<T>, listOfValues: List<T>) {
            collection.addAll(listOfValues)
            val element = collection.pop()
            assertAll(
                { Assertions.assertEquals(listOfValues.first(), element) },
                { Assertions.assertEquals(listOfValues.size - 1, collection.size) }
            )
        }

        fun checkPeekFromEmptyCollection(collection: MyCollection<*>) {
            val element = collection.peek()
            assertNull(element)
        }

        fun <T> checkPeekFromNotEmptyCollection(collection: MyCollection<T>, listOfValues: List<T>) {
            collection.addAll(listOfValues)
            val element = collection.peek()
            assertAll(
                { Assertions.assertEquals(listOfValues.first(), element) },
                { Assertions.assertEquals(listOfValues.size, collection.size) }
            )
        }
    }
}