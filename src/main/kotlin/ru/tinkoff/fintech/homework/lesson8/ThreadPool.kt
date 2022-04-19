package ru.tinkoff.fintech.homework.lesson8

import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue

class ThreadPool(
    amount: Int,
    tasks: Array<Runnable>,
) : Executor {

    private val tasksQueue: LinkedBlockingQueue<Runnable> = tasks.toCollection(LinkedBlockingQueue())
    private val threads: List<WorkerThread> = (0 until amount).map { workerThreadInstance() }.toCollection(LinkedList())

    init {
        threads.forEach(Thread::start)
        Thread { run() }.start()
    }

    override fun execute(command: Runnable) {
        tasksQueue.add(command)
    }

    fun shutdown() {
        threads.filter { it.state == Thread.State.WAITING}.forEach(Thread::interrupt)
    }

    private fun run() {
        while (true) {
            threads.filter { it.state == Thread.State.WAITING }.forEach {
                if(tasksQueue.isNotEmpty()) (it as Object).notify()
            }
        }
    }

    private fun workerThreadInstance(): WorkerThread {
        return WorkerThread(tasksQueue::poll)
    }

    private class WorkerThread(
        private val popTask: () -> Runnable?
    ) : Thread() {
        override fun run() {
            val task = popTask.invoke()
            if (task != null)
                task.run()
            else
                (this as Object).wait()
        }
    }
}