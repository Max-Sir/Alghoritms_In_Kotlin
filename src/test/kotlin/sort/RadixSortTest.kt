package sort

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RadixSortTest {
    // Test to check if the function returns the same array if only one element is present
    @Test
    fun oneElementTest() {
        val array = listOf(3)
        val result = radixSort(array, bitMapper = { it })
        assertEquals(array, result)
    }

    // Test to check if the function returns a sorted array
    @Test
    fun sortingTest() {
        val array = listOf(3, 4, 2, 5, 1)
        val expectedResult = listOf(1, 2, 3, 4, 5)
        val result = radixSort(array, bitMapper = { it })
        assertEquals(expectedResult, result)
    }

    // Test to check if the function returns the same array if all elements are the same
    @Test
    fun sameElementTest() {
        val array = arrayListOf(3, 3, 3, 3, 3)
        val result = radixSort(array, bitMapper = { it })
        assertEquals(array, result)
    }

    // Test to check if the function works with a custom bitMapper
    @Test
    fun customBitMapperTest() {
        val array = listOf("a", "ab", "abc", "abcd")
        val expectedResult = listOf("a", "ab", "abc", "abcd")
        val result = radixSort(array, bitMapper = { it.length })
        assertEquals(expectedResult, result)
    }

    @Test
    fun `test Radix sort with empty array`() {
        // Test with an empty array
        val empty = listOf<Int>()
        val emptySorted = radixSort(empty, bitMapper = { it })
        assertEquals(empty, emptySorted)
    }

    @Test
    fun `test Radix sort with single element array`() {
        // Test with a single element array
        val single = listOf(1)
        val singleSorted = radixSort(single, bitMapper = { it })
        assertEquals(single, singleSorted)
    }

    @Test
    fun `test Radix sort with multiple elements array`() {
        // Test with an array of multiple elements
        val multiple = listOf(5, 9, 1, 4, 8, 7, 6, 2, 3)
        val multipleSorted = radixSort(multiple, bitMapper = { it })
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), multipleSorted)
    }

    @Test
    fun `test Radix sort with array of objects`() {
        // Test with an array of objects
        data class Dog(val age: Int)

        val foos = listOf(Dog(3), Dog(2), Dog(5), Dog(1), Dog(4))
        val foosSorted = radixSort(foos, bitMapper = { it.age })
        assertEquals(listOf(Dog(1), Dog(2), Dog(3), Dog(4), Dog(5)), foosSorted)
    }


    @Test
    fun testRadixSortEmptyArray() {
        val result = radixSort(emptyList<Int>(), bitMapper = { it })
        assertEquals(emptyList<Int>(), result)
    }

    @Test
    fun testRadixSortOneElement() {
        val result = radixSort(listOf(1), bitMapper = { it })
        assertEquals(listOf(1), result)
    }

    @Test
    fun testRadixSortSortedArray() {
        val result = radixSort(listOf(1, 2, 3, 4, 5), bitMapper = { it })
        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }

    @Test
    fun testRadixSortUnsortedArray() {
        val result = radixSort(listOf(2, 3, 4, 1, 5), bitMapper = { it })
        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }

    @Test
    fun testRadixSortWithBits() {
        val result = radixSort(listOf(1, 2, 3), bits = 4, bitMapper = { it })
        assertEquals(listOf(1, 2, 3), result)
    }

    @Test
    fun testRadixSortWithBitMapper() {
        val result = radixSort(listOf(1, 2, 3), bitMapper = { it % 2 })
        assertEquals(listOf(2, 1, 3), result)
    }

    @Test
    fun testRadixSortWithRecursive() {
        val result = radixSort(listOf(1, 3, 5, 2, 4), bitMapper = { it }, recursive = true)
        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }

    @Test
    fun testRadixSortWithDuplicatesRecursive() {
        val result = radixSort(listOf(1, 3, 5, 3, 2, 4), bitMapper = { it }, recursive = true)
        assertEquals(listOf(1, 2, 3, 3, 4, 5), result)
    }

    @Test
    fun testRadixSortWithLargeBits() {
        val result = radixSort(listOf(1, 2, 3, 4, 5), bits = 20, bitMapper = { it })
        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }

    @Test
    fun testRadixSortWithArrayOfArrays() {
        val result = radixSort(listOf(listOf(1, 2, 3), listOf(4, 5, 6)), bitMapper = { it[0] })
        assertEquals(listOf(listOf(1, 2, 3), listOf(4, 5, 6)), result)
    }
}