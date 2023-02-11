package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MyHashSetTest{
    // Unit Test 1: Test size()
    @Test
    fun testSize() {
        val hashSet = MyHashSet<String>()
        hashSet.add("a")
        hashSet.add("b")
        hashSet.add("c")
        assertEquals(3, hashSet.size())
    }

    // Unit Test 2: Test isEmpty()
    @Test
    fun testIsEmpty() {
        val hashSet = MyHashSet<String>()
        assertEquals(true, hashSet.isEmpty())
        hashSet.add("a")
        assertEquals(false, hashSet.isEmpty())
    }

    // Unit Test 3: Test contains()
    @Test
    fun testContains() {
        val hashSet = MyHashSet<String>()
        hashSet.add("a")
        hashSet.add("b")
        hashSet.add("c")
        assertEquals(true, hashSet.contains("a"))
        assertEquals(false, hashSet.contains("d"))
    }

    // Unit Test 4: Test add()
    @Test
    fun testAdd() {
        val hashSet = MyHashSet<String>()
        assertEquals(true, hashSet.add("a"))
        assertEquals(false, hashSet.add("a"))
    }

    // Unit Test 5: Test remove()
    @Test
    fun testRemove() {
        val hashSet = MyHashSet<String>()
        hashSet.add("a")
        hashSet.add("b")
        hashSet.add("c")
        assertEquals(true, hashSet.remove("a"))
        assertEquals(false, hashSet.remove("d"))
    }

    // Unit Test 6: Test clear()
    @Test
    fun testClear() {
        val hashSet = MyHashSet<String>()
        hashSet.add("a")
        hashSet.add("b")
        hashSet.add("c")
        hashSet.clear()
        assertEquals(0, hashSet.size())
        assertEquals(true, hashSet.isEmpty())
    }

    // Unit Test 7: Test elements()
    @Test
    fun testElements() {
        val hashSet = MyHashSet<String>()
        hashSet.add("a")
        hashSet.add("b")
        hashSet.add("c")
        val elements = hashSet.elements()
        assertEquals(3, elements.size)
        assertEquals(true, elements.contains("a"))
        assertEquals(true, elements.contains("b"))
        assertEquals(true, elements.contains("c"))
    }

    // Unit Test 8: Test addAll()
    @Test
    fun testAddAll() {
        val hashSet1 = MyHashSet<String>()
        hashSet1.add("a")
        hashSet1.add("b")
        hashSet1.add("c")

        val hashSet2 = MyHashSet<String>()
        hashSet2.add("d")
        hashSet2.add("e")
        hashSet2.add("f")

        assertEquals(true, hashSet1.addAll(hashSet2))
        assertEquals(6, hashSet1.size())
        assertEquals(true, hashSet1.contains("a"))
        assertEquals(true, hashSet1.contains("b"))
        assertEquals(true, hashSet1.contains("c"))
        assertEquals(true, hashSet1.contains("d"))
        assertEquals(true, hashSet1.contains("e"))
        assertEquals(true, hashSet1.contains("f"))
    }

    // Unit Test 9: Test removeAll()
    @Test
    fun testRemoveAll() {
        val hashSet1 = MyHashSet<String>()
        hashSet1.add("a")
        hashSet1.add("b")
        hashSet1.add("c")
        hashSet1.add("d")
        hashSet1.add("e")
        hashSet1.add("f")

        val hashSet2 = MyHashSet<String>()
        hashSet2.add("a")
        hashSet2.add("b")
        hashSet2.add("c")

        assertEquals(true, hashSet1.removeAll(hashSet2))
        assertEquals(3, hashSet1.size())
        assertEquals(false, hashSet1.contains("a"))
        assertEquals(false, hashSet1.contains("b"))
        assertEquals(false, hashSet1.contains("c"))
        assertEquals(true, hashSet1.contains("d"))
        assertEquals(true, hashSet1.contains("e"))
        assertEquals(true, hashSet1.contains("f"))
    }

    // Unit Test 10: Test containsAll()
    @Test
    fun testContainsAll() {
        val hashSet1 = MyHashSet<String>()
        hashSet1.add("a")
        hashSet1.add("b")
        hashSet1.add("c")

        val hashSet2 = MyHashSet<String>()
        hashSet2.add("a")
        hashSet2.add("b")
        hashSet2.add("c")

        assertEquals(true, hashSet1.containsAll(hashSet2))

        hashSet2.add("d")
        assertEquals(false, hashSet1.containsAll(hashSet2))
    }

    // Unit Test 11: Test retainAll()
    @Test
    fun testRetainAll() {
        val hashSet1 = MyHashSet<String>()
        hashSet1.add("a")
        hashSet1.add("b")
        hashSet1.add("c")
        hashSet1.add("d")
        hashSet1.add("e")
        hashSet1.add("f")

        val hashSet2 = MyHashSet<String>()
        hashSet2.add("a")
        hashSet2.add("b")
        hashSet2.add("c")

        assertEquals(true, hashSet1.retainAll(hashSet2))
        assertEquals(3, hashSet1.size())
        assertEquals(true, hashSet1.contains("a"))
        assertEquals(true, hashSet1.contains("b"))
        assertEquals(true, hashSet1.contains("c"))
        assertEquals(false, hashSet1.contains("d"))
        assertEquals(false, hashSet1.contains("e"))
        assertEquals(false, hashSet1.contains("f"))
    }
}