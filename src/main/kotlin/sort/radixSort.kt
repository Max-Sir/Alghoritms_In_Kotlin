package sort

import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

// This function implements radix sort for a generic type T.
// This can be used on any Collection in Kotlin.
fun <T> radixSort(array: Collection<T>, bits: Int = 10, bitMapper: (T) -> Int, recursive: Boolean = false): List<T> {
    // Base case, array is empty or only has one element
    if (array.size <= 1) return array.toList()

    // Create buckets for each bit range
    val buckets = (0 until bits).map { Collections.synchronizedList(mutableListOf<T>()) }

    // Create threadPool
    val threadPoolSize = Runtime.getRuntime().availableProcessors()
    val executorService = Executors.newFixedThreadPool(threadPoolSize)

    // Place each element of the array into the appropriate bucket
    array.forEach { element ->
        // Place the element into the correct bucket
        executorService.execute {
            val bitRange = bitMapper(element)
            buckets[bitRange].add(element)
        }
    }

    // Shutdown the threadPool
    executorService.shutdown()
    while (!executorService.isTerminated) {
        // Use a timeout to prevent an infinite loop
        executorService.awaitTermination(10, TimeUnit.MILLISECONDS)
    }

    val sortedBuckets = buckets.map { bucket ->
        if (recursive && bucket.size > 1) radixSort(bucket, bits, bitMapper)
        else bucket.sortedWith(compareBy { bitMapper(it) })
    }

    // Flatten the sorted buckets
    return sortedBuckets.flatten()
}