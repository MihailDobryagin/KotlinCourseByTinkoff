package ru.tinkoff.fintech.homework.lesson8

import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue

class ThreadPool(
    private val amount: Int,
    tasks: Array<Runnable> = emptyArray(),
) : Executor {
    private val tasksQueue: LinkedBlockingQueue<Runnable> = tasks.toCollection(LinkedBlockingQueue())
    private val threads: List<WorkerThread> = (0 until amount).map { workerThreadInstance() }.toCollection(LinkedList())

    private var isActive = false

    init {
        threads.forEach(Thread::start)
        isActive = true
        Thread { run() }.start()
    }

    override fun execute(command: Runnable) {
        tasksQueue.add(command)
    }

    fun shutdown() {
        isActive = false
        var isAllThreadsStopped = false
        while (!isAllThreadsStopped) {
            isAllThreadsStopped = true
            threads
                .forEach {
                    synchronized(it) {
                        if (it.state != Thread.State.TERMINATED) {
                            it.interrupt()
                            isAllThreadsStopped = false
                        }
                    }
                }
        }
    }

    private fun run() {
        while (isActive) {
            threads.filter { it.state == Thread.State.WAITING }.forEach {
                if (tasksQueue.isNotEmpty()) {
                    synchronized(it) {
                        (it as Object).notify()
                    }
                }
            }
        }
    }

    private fun workerThreadInstance(): WorkerThread {
        return WorkerThread(tasksQueue::poll)
    }

    private class WorkerThread(
        private val getTask: () -> Runnable?,
    ) : Thread() {
        override fun run() {
            print("$line$name START$line")
            while (!this.isInterrupted) {
                val task = getTask.invoke()
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
    }

    companion object {
        @JvmStatic
        val line = "\n____________________________________________________________\n"
    }
}