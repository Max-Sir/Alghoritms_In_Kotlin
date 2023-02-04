package sort

import HeapSort
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.reflect.jvm.isAccessible
import kotlin.test.assertEquals

internal class HeapSortTest {

    @Test
    fun `test heap sort with int array`() {
        val array = arrayOf(3, 5, 2, 1, 4)
        val expected = arrayOf(1, 2, 3, 4, 5)
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(expected, array)
    }

    @Test
    fun `test heap sort with float array`() {
        val array = arrayOf(3.2f, 5.1f, 2.3f, 1.4f, 4.5f)
        val expected = arrayOf(1.4f, 2.3f, 3.2f, 4.5f, 5.1f)
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(expected, array)
    }

    @Test
    fun `test heap sort with string array`() {
        val array = arrayOf("c", "e", "a", "b", "d")
        val expected = arrayOf("a", "b", "c", "d", "e")
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(expected, array)
    }

    @Test
    fun `test heap sort with empty array`() {
        val array = arrayOf<Int>()
        val expected = arrayOf<Int>()
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(expected, array)
    }

    @Test
    fun `test heap sort with single element array`() {
        val array = arrayOf(1)
        val expected = arrayOf(1)
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(expected, array)
    }

    @Test
    fun `test heap sort with duplicate elements array`() {
        val array = arrayOf(3, 2, 2, 1, 5)
        val expected = arrayOf(1, 2, 2, 3, 5)
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(expected, array)
    }

    @Test
    fun `test heap sort with negative elements array`() {
        val array = arrayOf(-2, -4, -1, -3, -5)
        val expected = arrayOf(-5, -4, -3, -2, -1)
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(expected, array)
    }

    @Test
    fun `test heap sort with large array`() {
        val array = arrayOf(10, 7, 8, 9, 1, 5, 6, 4, 3, 2)
        val expected = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(expected, array)
    }

    @Test
    fun `test heap sort with sorted array`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expected = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(expected, array)
    }

    @Test
    fun `test heap sort with reversed array`() {
        val array = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val expected = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(expected, array)
    }

    @Test
    fun sortTest() {
        val array = arrayOf(2, 3, 1, 4, 5)
        val heapSort = HeapSort(array)
        heapSort.sort()
        assertArrayEquals(array, arrayOf(1, 2, 3, 4, 5))
    }

    @Test
    fun buildMaxHeapTest() {
        val array = arrayOf(2, 3, 1, 4, 5)
        val heapSort = HeapSort(array)
        val method = HeapSort::class.java.getDeclaredMethod("buildMaxHeap")
        method.isAccessible = true
        method.invoke(heapSort)
        assertArrayEquals(array, arrayOf(5, 4, 1, 2, 3))
    }

    @Test
    fun getLeftIndexTest() {
        val heapSort = HeapSort(arrayOf(2, 3, 1, 4, 5))
        val method = HeapSort::class.java.getDeclaredMethod("getLeftIndex", Int::class.java)
        method.isAccessible = true
        val index = method.invoke(heapSort, 2) as Int
        assertEquals(index, 5)
    }

    @Test
    fun getRightIndexTest() {
        val heapSort = HeapSort(arrayOf(2, 3, 1, 4, 5))
        val method = HeapSort::class.java.getDeclaredMethod("getRightIndex", Int::class.java)
        method.isAccessible = true
        val index = method.invoke(heapSort, 2) as Int
        assertEquals(index, 6)
    }

    @Test
    fun heapifyTest() {
        val array = arrayOf(5, 4, 1, 3, 2)
        val heapSort = HeapSort(array)
        val method = HeapSort::class.members.single { it.name == "heapify" }
        method.isAccessible = true
        method.call(heapSort, array, 0, array.size)
        assertArrayEquals(array, arrayOf(5, 4, 1, 3, 2))
    }

    @Test
    fun swapTest() {
        val array = arrayOf(5, 4, 1, 3, 2)
        val heapSort = HeapSort(array)
        val method = HeapSort::class.members.single { it.name == "swap" }
        method.isAccessible = true
        method.call(heapSort, array, 0, 4)
        assertArrayEquals(array, arrayOf(2, 4, 1, 3, 5))
    }

    @Test
    fun `big array testing`() {
        val array = IntArray(1_000_000) { Random.nextInt(-10000, 10000) }.toTypedArray()
        val heapSort = HeapSort(array)
        val expected = array.sortedArray()
        heapSort.sort()
        assertArrayEquals(array, expected)
    }
}
