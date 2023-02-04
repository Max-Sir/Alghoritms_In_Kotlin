package custom

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.ReplaySubject
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture

internal class ThreadSafeSingletonTest {

    @Test
    fun testThreadSafeSingleton() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = ThreadSafeSingleton.getInstance()
        assertEquals(instance1, instance2)
    }

    @Test
    fun testThreadSafety() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = ThreadSafeSingleton.getInstance()
        assertTrue(instance1 === instance2)
    }

    @Test
    fun testThreadSafetyUsingKotlin() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = runBlocking {
            ThreadSafeSingleton.getInstance()
        }

        assertTrue(instance1 === instance2)
    }

    @Test
    fun testThreadSafetyUsingRxJavaKotlin() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = Single.fromCallable {
            ThreadSafeSingleton.getInstance()
        }.blockingGet()

        assertTrue(instance1 === instance2)
    }

    @Test
    fun testThreadSafetyUsingFlowable() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = Flowable.fromCallable {
            ThreadSafeSingleton.getInstance()
        }.blockingFirst()

        assertTrue(instance1 === instance2)
    }

    @Test
    fun testThreadSafetyUsingCoroutines() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = runBlocking {
            ThreadSafeSingleton.getInstance()
        }

        assertTrue(instance1 === instance2)
    }

    @Test
    fun testThreadSafetyUsingThreads() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = runBlocking {
            Thread {
                ThreadSafeSingleton.getInstance()
            }.start()
            Thread.sleep(1000)
            ThreadSafeSingleton.getInstance()
        }

        assertTrue(instance1 === instance2)
    }

    @Test
    fun testThreadSafetyUsingCoroutines10Times() {
        for (i in 1..10) {
            val instance1 = ThreadSafeSingleton.getInstance()
            val instance2 = runBlocking {
                ThreadSafeSingleton.getInstance()
            }

            assertTrue(instance1 === instance2)
        }
    }

    @Test
    fun testThreadSafetyUsingChannels() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = runBlocking {
            val channel = Channel<ThreadSafeSingleton>()
            launch {
                channel.send(ThreadSafeSingleton.getInstance())
            }
            channel.receive()
        }

        assertTrue(instance1 === instance2)
    }


    @Test
    fun testThreadSafetyUsingReplaySubject() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val replaySubject = ReplaySubject.create<ThreadSafeSingleton>()
        replaySubject.onNext(ThreadSafeSingleton.getInstance())
        val instance2 = replaySubject.blockingFirst()

        assertTrue(instance1 === instance2)
    }

    @Test
    fun testThreadSafetyUsingBehaviorSubject() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val behaviorSubject = BehaviorSubject.create<ThreadSafeSingleton>()
        behaviorSubject.onNext(ThreadSafeSingleton.getInstance())
        val instance2 = behaviorSubject.blockingFirst()

        assertTrue(instance1 === instance2)
    }

    @Test
    fun testThreadSafetyUsingFuture() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = runBlocking {
            val future = CompletableFuture.supplyAsync {
                ThreadSafeSingleton.getInstance()
            }
            future.get()
        }

        assertTrue(instance1 === instance2)
    }

    @Test
    fun testNegative() {
        // Create multiple threads to access the getInstance() method
        val threads = List(10) {
            Thread { ThreadSafeSingleton.getInstance() }
        }
        threads.forEach { it.start() }
        threads.forEach { it.join() }

        // Assert that only one instance of the ThreadSafeSingleton class was created
        assertEquals(1, threads.map { it.run { ThreadSafeSingleton.getInstance() } }.distinct().count())
    }

    @Test
    fun testThreadSafetyUsingCallable() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = runBlocking {
            val callable = Callable {
                ThreadSafeSingleton.getInstance()
            }
            callable.call()
        }

        assertTrue(instance1 === instance2)
    }

    @Test
    fun testThreadSafetyUsingSupervisorJob() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = runBlocking {
            val supervisorJob = SupervisorJob()
            var singleton: ThreadSafeSingleton? = null
            val job = launch(supervisorJob) {
                singleton = ThreadSafeSingleton.getInstance()
            }
            job.join()
            return@runBlocking singleton
        }

        assertTrue(instance1 === instance2)
    }

    @Test
    fun testThreadSafetyUsingDeferred1() {
        val instance1 = ThreadSafeSingleton.getInstance()
        val instance2 = runBlocking {
            async {
                ThreadSafeSingleton.getInstance()
            }.await()
        }

        assertTrue(instance1 === instance2)
    }
}