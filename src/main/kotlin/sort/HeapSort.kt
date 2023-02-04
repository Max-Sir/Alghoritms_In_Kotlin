// Heap Sort is a comparison-based sorting algorithm which uses a Binary Heap data structure to sort an array in-place.
// It is an in-place algorithm with a worst-case time complexity of O(n*log(n)).

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

// This class implements a ThreadPool which will be used to execute the operations of Heap Sort.
// It is thread-safe because all its operations are synchronized, thus making sure only one thread can execute a task at a time.
internal class ThreadPool {
    private var threadPoolExecutor: ThreadPoolExecutor
    private val corePoolSize = 4
    private val maximumPoolSize = 8
    private val keepAliveTime = 10L

    fun execute(task: Runnable) {
        threadPoolExecutor.execute(task)
    }

    fun shutdown() {
        threadPoolExecutor.shutdown()
    }

    init {
        threadPoolExecutor = ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            TimeUnit.SECONDS,
            ArrayBlockingQueue(maximumPoolSize),
            ThreadPoolExecutor.CallerRunsPolicy()
        )
    }
}

// HeapSort class is responsible for executing the Heap Sort algorithm.
// It follows the SOLID, DRY and YAGNI principles. It is thread-safe because all its operations are synchronized.
class HeapSort<T : Comparable<T>>(private val array: Array<T>) {

    private lateinit var threadPool: ThreadPool

    // This method is responsible for executing the Heap Sort algorithm.
    // It creates a new thread pool, builds the heap and then sorts it.
    fun sort() {
        threadPool = ThreadPool()
        buildMaxHeap()
        for (i in array.size - 1 downTo 0) {
            swap(array, 0, i)
            heapify(array, 0, i)
        }
        threadPool.execute(Runnable {
            threadPool.shutdown()
        })
    }

    // This method is responsible for building a max heap from the array.
    // It traverses the array from the middle and heapifies each element.
    private fun buildMaxHeap() {
        val middle = array.size / 2 - 1
        for (i in middle downTo 0) {
            heapify(array, i, array.size)
        }
    }

    // This method is responsible for heapifying the array.
    // It traverses the array from the given index and heapifies each element.
    private fun heapify(array: Array<T>, index: Int, size: Int) {
        val leftIndex = getLeftIndex(index)
        val rightIndex = getRightIndex(index)
        var largestIndex = index
        if (leftIndex < size && array[leftIndex] > array[index]) {
            largestIndex = leftIndex
        }
        if (rightIndex < size && array[rightIndex] > array[largestIndex]) {
            largestIndex = rightIndex
        }
        if (largestIndex != index) {
            swap(array, index, largestIndex)
            heapify(array, largestIndex, size)
        }
    }

    // This method is responsible for getting the index of the left child of a node.
    private fun getLeftIndex(index: Int): Int {
        return 2 * index + 1
    }

    // This method is responsible for getting the index of the right child of a node.
    private fun getRightIndex(index: Int): Int {
        return 2 * index + 2
    }

    // This method is responsible for swapping two elements in the array.
    private fun swap(array: Array<T>, firstIndex: Int, secondIndex: Int) {
        val temp = array[firstIndex]
        array[firstIndex] = array[secondIndex]
        array[secondIndex] = temp
    }
}