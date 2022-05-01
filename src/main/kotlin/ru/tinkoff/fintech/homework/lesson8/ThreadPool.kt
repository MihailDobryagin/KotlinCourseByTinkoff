package ru.tinkoff.fintech.homework.lesson8

import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue

class ThreadPool(
    private val amount: Int = 1,
) : Executor {
    companion object {
        @JvmStatic
        val line = "\n____________________________________________________________\n"
    }

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
        if (!isActive) throw IllegalStateException("Все потоки завершены")
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
                    if (it.status != ThreadExecutionStatus.TERMINATED) {
                        it.interrupt()
                        isAllThreadsStopped = false
                    }
                }
            }
        }
    }

    fun countOfActiveThreads(): Int = threads.count { it.status == ThreadExecutionStatus.RUNNING }

    private fun notifyWaitingThreads() {
        threads.find { it.status == ThreadExecutionStatus.WAITING }?.let {
            if (tasksQueue.isNotEmpty()) {
                synchronized(it) {
                    if (it.status == ThreadExecutionStatus.WAITING)
                        (it as Object).notify()
                }
            }
        }
    }

    enum class ThreadExecutionStatus {
        RUNNING,
        WAITING,
        TERMINATED,
    }

    private inner class WorkerThread : Thread() {
        var status: ThreadExecutionStatus = ThreadExecutionStatus.WAITING
            private set

        override fun interrupt() {
            status = ThreadExecutionStatus.TERMINATED
            super.interrupt()
        }

        override fun run() {
            status = ThreadExecutionStatus.RUNNING
            print("$line$name START$line")
            while (status != ThreadExecutionStatus.TERMINATED) {
                val task = getTask()
                if (task != null) {
                    print("$line$name TAKE$line") // TAKE TASK
                    task.run()
                } else {
                    synchronized(this) {
                        try {
                            status = ThreadExecutionStatus.WAITING
                            (this as Object).wait()
                            status = ThreadExecutionStatus.RUNNING
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
}