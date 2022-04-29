package ru.tinkoff.fintech.homework.lesson8

import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue

class ThreadPool(
    private val amount: Int = 1,
) : Executor {
    private val limitOfThreads = 100

    private val tasksQueue: LinkedBlockingQueue<Runnable> = LinkedBlockingQueue()
    private val threads: List<WorkerThread> = (0 until amount)
        .map { WorkerThread() }.toCollection(LinkedList())

    private var isActive = false

    init {
        if (amount > limitOfThreads) throw IllegalArgumentException("Превышено максимальное кол-во потоков ($limitOfThreads)")
        isActive = true
        threads.forEach(Thread::start)
    }

    override fun execute(command: Runnable) {
        if(!isActive) throw IllegalStateException("Все потоки завершены")
        tasksQueue.add(command)
        notifyWaitingThreads()
    }

    fun shutdown() {
        isActive = false
        var isAllThreadsStopped = false
        while (!isAllThreadsStopped) {
            isAllThreadsStopped = true
            threads.forEach {
                synchronized(it) {
                    if (it.state != Thread.State.TERMINATED) {
                        it.interrupt()
                        isAllThreadsStopped = false
                    }
                }
            }
        }
    }

    private fun notifyWaitingThreads() {
        threads.filter { it.state == Thread.State.WAITING}.forEach {
            if (tasksQueue.isNotEmpty()) {
                synchronized(it) {
                    if(it.state == Thread.State.WAITING)
                        (it as Object).notify()
                }
            }
        }
    }

    private inner class WorkerThread : Thread() {
        override fun run() {
            print("$line$name START$line")
            while (!this.isInterrupted) {
                val task = getTask()
                if (task != null) {
                    print("$line$name TAKE$line") // TAKE TASK
                    task.run()
                } else {
                    synchronized(this) {
                        try {
                            (this as Object).wait()
                        } catch (e: InterruptedException) {
                            this.interrupt()
                            print("$line$name TERMINATE$line")
                        }
                    }
                }
            }
        }

        private fun getTask(): Runnable? = tasksQueue.poll()
    }

    companion object {
        @JvmStatic
        val line = "\n____________________________________________________________\n"
    }
}