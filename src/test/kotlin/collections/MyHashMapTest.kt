package collections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyHashMapTest {

    private val myHashMap = MyHashMap<String, Int>()

    @Test
    fun testSize() {
        assertEquals(0, myHashMap.size())
        myHashMap.put("1", 1)
        assertEquals(1, myHashMap.size())
    }

    @Test
    fun testThreshold() {
        assertEquals(0, myHashMap.threshold())
        myHashMap.put("1", 1)
        assertEquals(1, myHashMap.threshold())
    }

    @Test
    fun testLoadFactor() {
        assertEquals(0.75F, myHashMap.loadFactor(), 0.001F)
    }

    @Test
    fun testIsEmpty() {
        assertTrue(myHashMap.isEmpty())
        myHashMap.put("1", 1)
        assertFalse(myHashMap.isEmpty())
    }

    @Test
    fun testContainsKey() {
        assertFalse(myHashMap.containsKey("1"))
        myHashMap.put("1", 1)
        assertTrue(myHashMap.containsKey("1"))
    }

    @Test
    fun testContainsValue() {
        assertFalse(myHashMap.containsValue(1))
        myHashMap.put("1", 1)
        assertTrue(myHashMap.containsValue(1))
    }

    @Test
    fun testGet() {
        assertNull(myHashMap.get("1"))
        myHashMap.put("1", 1)
        assertEquals(1, myHashMap.get("1"))
    }

    @Test
    fun testPut() {
        assertNull(myHashMap.put("1", 1))
        assertEquals(1, myHashMap.put("1", 2))
    }

    @Test
    fun testRemove() {
        assertNull(myHashMap.remove("1"))
        myHashMap.put("1", 1)
        assertEquals(1, myHashMap.remove("1"))
    }

    @Test
    fun testPutAll() {
        val map = MyHashMap<String, Int>()
        map.put("1", 1)
        map.put("2", 2)
        myHashMap.putAll(map)
        assertEquals(2, myHashMap.size())
    }

    @Test
    fun testClear() {
        myHashMap.put("1", 1)
        myHashMap.clear()
        assertEquals(0, myHashMap.size())
    }

    @Test
    fun testKeySet() {
        myHashMap.put("1", 1)
        myHashMap.put("2", 2)
        val set = myHashMap.keySet()
        assertTrue(set.contains("1"))
        assertTrue(set.contains("2"))
    }

    @Test
    fun testValues() {
        myHashMap.put("1", 1)
        myHashMap.put("2", 2)
        val list = myHashMap.values()
        assertTrue(list.contains(1))
        assertTrue(list.contains(2))
    }

    @Test
    fun testEntrySet() {
        myHashMap.put("1", 1)
        myHashMap.put("2", 2)
        val set = myHashMap.entrySet()
        assertEquals(2, set.size)
    }
}