package ru.tinkoff.fintech.homework.lesson8

fun main() {
    val tasks = Array(3) {
        var counter = 0
        Runnable {
            for (i in 0 until 20 * (it + 1)) {
                println("Поток $it -- $counter итерация")
                counter++
            }
        }
    }

    val threadPool = ThreadPool(3, tasks)
    Thread.sleep(2000)
    threadPool.shutdown()
}