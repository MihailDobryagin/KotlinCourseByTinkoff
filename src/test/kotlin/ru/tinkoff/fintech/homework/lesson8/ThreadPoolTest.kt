package ru.tinkoff.fintech.homework.lesson8

import io.mockk.clearAllMocks
import io.mockk.spyk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.lang.Integer.max
import java.lang.Thread.sleep

class ThreadPoolTest {
    private val numOfThreads = 5
    private val threadPool: ThreadPool = spyk(ThreadPool(numOfThreads))

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun checkThreadPool() {
        var actual = 0
        val task = { for (i in 0 until 123) actual++ }

        threadPool.execute(task)
        sleep(1000)
        threadPool.shutdown()

        val expected = 123
        assertEquals(expected, actual)
    }

    @Test
    fun checkExecutionWithManyTasks() {
        val sumOfAllNumbers = 5050
        var actual = 0
        val tasks = Array(100) {
            Runnable {
                var maxNum = 0
                for (i in 1 until it + 2) {
                    maxNum = max(maxNum, i)
                }
                actual += maxNum
            }
        }

        tasks.forEach(threadPool::execute)
        sleep(3000)
        threadPool.shutdown()

        assertEquals(sumOfAllNumbers, actual)
    }

    @Test
    fun checkShutdown() {
        val sumOfAllNumbers = 5050
        var actual = 0
        val tasks = Array(100) {
            Runnable {
                var maxNum = 0
                for (i in 1 until it + 2) {
                    maxNum = max(maxNum, i)
                }
                sleep(1000)
                actual += maxNum
            }
        }

        tasks.forEach(threadPool::execute)
        threadPool.shutdown()

        assertNotEquals(sumOfAllNumbers, actual)
    }

    @Test
    fun checkCountOfActiveThreads() {
        var actual = 0
        val tasks = Array(100) {
            Runnable {
                actual++
                var maxNum = 0
                for (i in 1 until it + 2) {
                    maxNum = max(maxNum, i)
                }
                sleep(1000)
            }
        }

        tasks.forEach(threadPool::execute)
        sleep(100)

        assertEquals(5, actual)
    }
}