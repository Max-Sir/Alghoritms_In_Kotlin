package collections

import org.junit.Assert
import org.junit.Test

class MyHashMapTest {

    private val myHashMap = MyHashMap<String, Int>()

    @Test
    fun testSize() {
        Assert.assertEquals(0, myHashMap.size())
        myHashMap.put("1", 1)
        Assert.assertEquals(1, myHashMap.size())
    }

    @Test
    fun testThreshold() {
        Assert.assertEquals(0, myHashMap.threshold())
        myHashMap.put("1", 1)
        Assert.assertEquals(1, myHashMap.threshold())
    }

    @Test
    fun testLoadFactor() {
        Assert.assertEquals(0.75F, myHashMap.loadFactor(), 0.001F)
    }

    @Test
    fun testIsEmpty() {
        Assert.assertTrue(myHashMap.isEmpty())
        myHashMap.put("1", 1)
        Assert.assertFalse(myHashMap.isEmpty())
    }

    @Test
    fun testContainsKey() {
        Assert.assertFalse(myHashMap.containsKey("1"))
        myHashMap.put("1", 1)
        Assert.assertTrue(myHashMap.containsKey("1"))
    }

    @Test
    fun testContainsValue() {
        Assert.assertFalse(myHashMap.containsValue(1))
        myHashMap.put("1", 1)
        Assert.assertTrue(myHashMap.containsValue(1))
    }

    @Test
    fun testGet() {
        Assert.assertNull(myHashMap.get("1"))
        myHashMap.put("1", 1)
        Assert.assertEquals(1, myHashMap.get("1"))
    }

    @Test
    fun testPut() {
        Assert.assertNull(myHashMap.put("1", 1))
        Assert.assertEquals(1, myHashMap.put("1", 2))
    }

    @Test
    fun testRemove() {
        Assert.assertNull(myHashMap.remove("1"))
        myHashMap.put("1", 1)
        Assert.assertEquals(1, myHashMap.remove("1"))
    }

    @Test
    fun testPutAll() {
        val map = MyHashMap<String, Int>()
        map.put("1", 1)
        map.put("2", 2)
        myHashMap.putAll(map)
        Assert.assertEquals(2, myHashMap.size())
    }

    @Test
    fun testClear() {
        myHashMap.put("1", 1)
        myHashMap.clear()
        Assert.assertEquals(0, myHashMap.size())
    }

    @Test
    fun testKeySet() {
        myHashMap.put("1", 1)
        myHashMap.put("2", 2)
        val set = myHashMap.keySet()
        Assert.assertTrue(set.contains("1"))
        Assert.assertTrue(set.contains("2"))
    }

    @Test
    fun testValues() {
        myHashMap.put("1", 1)
        myHashMap.put("2", 2)
        val list = myHashMap.values()
        Assert.assertTrue(list.contains(1))
        Assert.assertTrue(list.contains(2))
    }

    @Test
    fun testEntrySet() {
        myHashMap.put("1", 1)
        myHashMap.put("2", 2)
        val set = myHashMap.entrySet()
        Assert.assertEquals(2, set.size)
    }
}