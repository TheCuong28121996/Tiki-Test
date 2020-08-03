package tiki.com.vn.utils

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicBoolean

class Worker(task: Runnable, latch: CountDownLatch) : Runnable {

    private val started: AtomicBoolean
    private val task: Runnable
    private val thread: Thread
    private val latch: CountDownLatch

    fun start() {
        if (!started.getAndSet(true)) {
            thread.start()
        }
    }

    override fun run() {
        task.run()
        latch.countDown()
    }

    init {
        this.latch = latch
        this.task = task
        started = AtomicBoolean(false)
        thread = Thread(this)
    }
}