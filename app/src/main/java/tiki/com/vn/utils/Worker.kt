package tiki.com.vn.utils

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicBoolean

class Worker(private val task: Runnable, private val latch: CountDownLatch) : Runnable {

    private val started: AtomicBoolean = AtomicBoolean(false)
    private val thread: Thread = Thread(this)

    fun start() {
        if (!started.getAndSet(true)) {
            thread.start()
        }
    }

    override fun run() {
        task.run()
        latch.countDown()
    }

}