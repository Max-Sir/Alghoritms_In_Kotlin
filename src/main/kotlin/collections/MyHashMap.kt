package collections

import java.util.ArrayList

class MyHashMap<K, V> {

    private var table: ArrayList<Node<K, V>>
    private var size: Int
    private var loadFactor: Float
    private var threshold: Int

    constructor() {
        this.size = 0
        this.table = ArrayList()
        this.loadFactor = 0.75F
        this.threshold = (this.table.size * this.loadFactor).toInt()
    }

    constructor(loadFactor: Float) {
        this.size = 0
        this.table = ArrayList()
        this.loadFactor = loadFactor
        this.threshold = (this.table.size * this.loadFactor).toInt()
    }

    // Method 1: Return the size of the MyHashMap
    fun size(): Int {
        return this.size
    }

    // Method 2: Return the threshold of the MyHashMap
    fun threshold(): Int {
        return this.threshold
    }

    // Method 3: Return the load factor of the MyHashMap
    fun loadFactor(): Float {
        return this.loadFactor
    }

    // Method 4: Return true if the MyHashMap is empty
    fun isEmpty(): Boolean {
        return this.size == 0
    }

    // Method 5: Return true if the MyHashMap contains the specified key
    fun containsKey(key: K): Boolean {
        return this.get(key) != null
    }

    // Method 6: Return true if the MyHashMap contains the specified value
    fun containsValue(value: V): Boolean {
        for (node in this.table) {
            if (node.value == value) {
                return true
            }
        }
        return false
    }

    // Method 7: Return the value associated with the specified key
    fun get(key: K): V? {
        for (node in this.table) {
            if (node.key == key) {
                return node.value
            }
        }
        return null
    }

    // Method 8: Insert the specified key-value pair into the MyHashMap
    fun put(key: K, value: V): V? {
        var oldValue: V? = null
        for (node in this.table) {
            if (node.key == key) {
                oldValue = node.value
                node.value = value
                break
            }
        }
        if (oldValue == null) {
            this.table.add(Node(key, value))
            this.size++
        }

        if (this.size >= this.threshold) {
            this.resize()
        }
        return oldValue
    }

    // Method 9: Remove the specified key from the MyHashMap
    fun remove(key: K): V? {
        var oldValue: V? = null
        for (node in this.table) {
            if (node.key == key) {
                oldValue = node.value
                this.table.remove(node)
                this.size--
                break
            }
        }
        return oldValue
    }

    // Method 10: Put all key-value pairs from the specified map into this MyHashMap
    fun putAll(map: MyHashMap<K, V>) {
        for (node in map.table) {
            this.put(node.key, node.value)
        }
    }

    // Method 11: Clear the MyHashMap
    fun clear() {
        this.table.clear()
        this.size = 0
    }

    // Method 12: Return the set of keys in the MyHashMap
    fun keySet(): Set<K> {
        val set: MutableSet<K> = mutableSetOf()
        for (node in this.table) {
            set.add(node.key)
        }
        return set
    }

    // Method 13: Return the collection of values in the MyHashMap
    fun values(): Collection<V> {
        val list: MutableList<V> = mutableListOf()
        for (node in this.table) {
            list.add(node.value)
        }
        return list
    }

    // Method 14: Return the set of key-value pairs in the MyHashMap
    fun entrySet(): Set<Map.Entry<K, V>> {
        val set: MutableSet<Map.Entry<K, V>> = mutableSetOf()
        for (node in this.table) {
            set.add(Node(node.key, node.value))
        }
        return set
    }

    // Method 15: Resize the MyHashMap
    private fun resize() {
        val newTable: ArrayList<Node<K, V>> = ArrayList()
        for (node in this.table) {
            newTable.add(node)
        }
        this.table = newTable
        this.threshold = (this.table.size * this.loadFactor).toInt()
    }

    // Method 16: Return the hash code for the specified key
    private fun hash(key: K): Int {
        var hashCode = 0
        for (char in key.toString()) {
            hashCode += char.toInt()
        }
        return hashCode
    }

    // Method 17: Return the index for the specified hash code
    private fun indexFor(hashCode: Int): Int {
        return hashCode % this.table.size
    }

    // Method 18: Return the node at the specified index
    private fun nodeAt(index: Int): Node<K, V>? {
        return if (index < this.table.size) this.table[index] else null
    }

    // Method 19: Return the next node in the linked list
    private fun nextNode(node: Node<K, V>?): Node<K, V>? {
        val index = this.indexFor(this.hash(node?.key!!))
        if (index == this.table.size - 1) {
            return null
        }
        return this.table[index + 1]
    }

    // Method 20: Return the previous node in the linked list
    private fun prevNode(node: Node<K, V>?): Node<K, V>? {
        val index = this.indexFor(this.hash(node?.key!!))
        if (index == 0) {
            return null
        }
        return this.table[index - 1]
    }

    // Node class for storing key-value pairs
    data class Node<K, V>(override val key: K, override var value: V) : Map.Entry<K, V>
}


fun main() {
    val map = MyHashMap<String, String>()
    println("Size of HashMap: ${map.size()}")
    println("Threshold of HashMap: ${map.threshold()}")
    println("Load factor of HashMap: ${map.loadFactor()}")
    println("Is empty: ${map.isEmpty()}")

    map.put("A", "Apple")
    map.put("B", "Banana")
    map.put("C", "Cherry")

    println("Size of HashMap: ${map.size()}")
    println("Threshold of HashMap: ${map.threshold()}")

    println("Contains 'A'? ${map.containsKey("A")}")
    println("Contains 'Apple'? ${map.containsValue("Apple")}")
    println("Value at 'A': ${map.get("A")}")

    map.put("A", "Avocado")
    println("Value at 'A': ${map.get("A")}")

    map.remove("A")
    println("Size of HashMap: ${map.size()}")

    val newMap = MyHashMap<String, String>()
    newMap.put("D", "Durian")
    newMap.put("E", "Eggfruit")
    newMap.put("E","Frateizer")
    newMap.put("F", "Fig")
    map.putAll(newMap)
    println("Size of HashMap: ${map.size()}")

    println("Key set: ${map.keySet()}")
    println("Value set: ${map.values()}")
    println("Entry set: ${map.entrySet()}")

    map.clear()
    println("Size of HashMap: ${map.size()}")
}