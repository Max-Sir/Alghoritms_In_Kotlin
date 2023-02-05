package sort

import java.util.Random

object BogoSort {

    // Applies Bogo Sort algorithm to given array
    fun sort(arr: IntArray): IntArray {

        // If array is empty
        // or contains a single element
        // then it is already sorted
        if (arr.size <= 1)
            return arr

        // Sorting loop
        while (!isSorted(arr)) {
            shuffle(arr)
        }

        return arr
    }

    // Checks if array is sorted
    private fun isSorted(arr: IntArray): Boolean {
        for (i in 0 until arr.size - 1)
            if (arr[i] > arr[i + 1])
                return false

        return true
    }

    // Shuffles elements of array randomly
    private fun shuffle(arr: IntArray) {
        val r = Random()
        for (i in arr.size - 1 downTo 0) {
            val j = r.nextInt(i + 1)
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }
}

// Thread safety
// BogoSort is thread-safe as it does not share any state between threads.

// SOLID Principles
// Single Responsibility Principle: BogoSort class has only one responsibility which is to sort an array.

// Open/Closed Principle: BogoSort class is open for extension as we can add more sorting algorithms in the future.

// Liskov Substitution Principle: We can use BogoSort class to sort any array.

// Interface Segregation Principle: BogoSort class does not implement any interface.

// Dependency Inversion Principle: BogoSort class does not depend on any other class and can be used independently.