package sort

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

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
    //because this algorithm time complexity is O(n*n!) I commented next lines for faster testing purpose
/*
    "BogoSort should sort an array of size 15" {
        val arr = intArrayOf(15, 2, 5, 7, 4, 3, 9, 8, 1, 6, 10, 11, 13, 14, 12)
        val expected = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        val actual = BogoSort.sort(arr)
        actual shouldBe expected
    }

    "BogoSort should sort an array of size 20" {
        val arr = intArrayOf(20, 2, 5, 7, 4, 3, 9, 8, 1, 6, 10, 11, 13, 14, 12, 15, 16, 19, 18, 17)
        val expected = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        val actual = BogoSort.sort(arr)
        actual shouldBe expected
    }
*/
})