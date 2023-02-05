package sort

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.isAccessible

class BogoSortTest : StringSpec({

    "BogoSort should sort an empty array" {
        val arr = intArrayOf()
        val expected = intArrayOf()
        val actual = BogoSort.sort(arr)
        actual shouldBe expected
    }

    "BogoSort should sort an array of size 1" {
        val arr = intArrayOf(1)
        val expected = intArrayOf(1)
        val actual = BogoSort.sort(arr)
        actual shouldBe expected
    }

    "BogoSort should sort an array of size 2" {
        val arr = intArrayOf(2, 1)
        val expected = intArrayOf(1, 2)
        val actual = BogoSort.sort(arr)
        actual shouldBe expected
    }

    "BogoSort should sort an array of size 3" {
        val arr = intArrayOf(2, 3, 1)
        val expected = intArrayOf(1, 2, 3)
        val actual = BogoSort.sort(arr)
        actual shouldBe expected
    }

    "BogoSort should sort an array of size 4" {
        val arr = intArrayOf(2, 4, 3, 1)
        val expected = intArrayOf(1, 2, 3, 4)
        val actual = BogoSort.sort(arr)
        actual shouldBe expected
    }

    "BogoSort should sort an array of size 5" {
        val arr = intArrayOf(2, 5, 4, 3, 1)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val actual = BogoSort.sort(arr)
        actual shouldBe expected
    }

    "BogoSort should sort an array of size 10" {
        val arr = intArrayOf(10, 2, 5, 7, 4, 3, 9, 8, 1, 6)
        val expected = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val actual = BogoSort.sort(arr)
        actual shouldBe expected
    }

    "Bogo sort should sort an array" {
        val arr = intArrayOf(3, 5, 2, 7, 1)
        val expected = intArrayOf(1, 2, 3, 5, 7)

        BogoSort.sort(arr) shouldBe expected
    }

    "isSorted should check if array is sorted" {
        val sortedArray = intArrayOf(1, 2, 3, 5, 7)
        val unsortedArray = intArrayOf(3, 5, 2, 7, 1)

        BogoSort::class.memberFunctions.find { it.name == "isSorted" }?.also { it.isAccessible = true }
            ?.call(BogoSort, sortedArray) shouldBe true
        BogoSort::class.memberFunctions.find { it.name == "isSorted" }?.also { it.isAccessible = true }
            ?.call(BogoSort, unsortedArray) shouldBe false
    }

    "shuffle should randomize the elements of an array" {
        val arr = intArrayOf(1, 2, 3, 4, 5)

        BogoSort::class.memberFunctions.find { it.name == "shuffle" }?.also {
            it.isAccessible = true
        }?.call(BogoSort, arr)

        arr shouldNotBe intArrayOf(1, 2, 3, 4, 5)
    }

})