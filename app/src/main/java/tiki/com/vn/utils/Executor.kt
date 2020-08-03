package tiki.com.vn.utils
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CountDownLatch


class Executor private constructor(
    tasks: List<Runnable>,
    private val callback: Callback?) : Thread() {

    private val workers: ConcurrentLinkedQueue<Worker> = ConcurrentLinkedQueue()
    private val latch: CountDownLatch = CountDownLatch(tasks.size)

    fun execute() {
        start()
    }

    override fun run() {
        while (true) {
            val worker = workers.poll() ?: break
            worker.start()
        }
        try {
            latch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        callback?.onComplete()
    }

    class Builder {
        private val tasks: MutableList<Runnable> = ArrayList()
        private var callback: Callback? = null

        fun add(task: Runnable): Builder {
            tasks.add(task)
            return this
        }

        fun callback(callback: Callback?): Builder {
            this.callback = callback
            return this
        }

        fun build(): Executor {
            return Executor(tasks, callback)
        }
    }

    interface Callback {
        fun onComplete()
    }

    init {
        for (item in tasks) {
            workers.add(Worker(item, latch))
        }
    }
}