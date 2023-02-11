package collections

import java.util.function.Predicate

class MyHashSet<T> {

    private var map: MyHashMap<T, Any?>
    private var size: Int

    constructor() {
        this.map = MyHashMap()
        this.size = 0
    }

    constructor(loadFactor: Float) {
        this.map = MyHashMap(loadFactor)
        this.size = 0
    }

    // Method 1: Return the size of the MyHashSet
    fun size(): Int {
        return this.size
    }

    // Method 2: Return true if the MyHashSet is empty
    fun isEmpty(): Boolean {
        return this.size == 0
    }

    // Method 3: Return true if the MyHashSet contains the specified element
    fun contains(element: T): Boolean {
        return this.map.containsKey(element)
    }

    // Method 4: Add the specified element to the MyHashSet
    fun add(element: T): Boolean {
        if (this.map.containsKey(element)) {
            return false
        }
        this.map.put(element, null)
        this.size++
        return true
    }

    // Method 5: Remove the specified element from the MyHashSet
    fun remove(element: T): Boolean {
        if (this.map.containsKey(element)) {
            this.map.remove(element)
            this.size--
            return true
        }
        return false
    }

    // Method 6: Clear the MyHashSet
    fun clear() {
        this.map.clear()
        this.size = 0
    }

    // Method 7: Return a set view of the elements in the MyHashSet
    fun elements(): Set<T> {
        return this.map.keySet()
    }


    // Method 8: Add all of the elements from another MyHashSet to the current MyHashSet
    fun addAll(otherSet: MyHashSet<T>): Boolean {
        if (otherSet.isEmpty()) {
            return false
        }

        for (element in otherSet.elements()) {
            this.add(element)
        }

        return true
    }

    // Method 9: Remove all of the elements from another MyHashSet from the current MyHashSet
    fun removeAll(otherSet: MyHashSet<T>): Boolean {
        if (otherSet.isEmpty()) {
            return false
        }

        for (element in otherSet.elements()) {
            this.remove(element)
        }

        return true
    }

    // Method 10: Return true if the current MyHashSet contains all of the elements from another MyHashSet
    fun containsAll(otherSet: MyHashSet<T>): Boolean {
        if (otherSet.isEmpty()) {
            return false
        }

        for (element in otherSet.elements()) {
            if (!this.contains(element)) {
                return false
            }
        }

        return true
    }

    // Method 11: Retain only the elements in the current MyHashSet that are also in another MyHashSet
    fun retainAll(otherSet: MyHashSet<T>): Boolean {
        if (otherSet.isEmpty()) {
            return false
        }

        val toRemove = ArrayList<T>()
        for (element in this.elements()) {
            if (!otherSet.contains(element)) {
                toRemove.add(element)
            }
        }

        for (element in toRemove) {
            this.remove(element)
        }

        return true
    }

    // Method 12: Return true if the current MyHashSet is equal to another MyHashSet
    fun equals(otherSet: MyHashSet<T>): Boolean {
        if (otherSet.size() != this.size()) {
            return false
        }

        for (element in otherSet.elements()) {
            if (!this.contains(element)) {
                return false
            }
        }

        return true
    }

    // Method 13: Return true if the current MyHashSet is a subset of another MyHashSet
    fun isSubsetOf(otherSet: MyHashSet<T>): Boolean {
        if (otherSet.size() < this.size()) {
            return false
        }

        for (element in this.elements()) {
            if (!otherSet.contains(element)) {
                return false
            }
        }

        return true
    }

    // Method 14: Return true if the current MyHashSet is a superset of another MyHashSet
    fun isSupersetOf(otherSet: MyHashSet<T>): Boolean {
        if (otherSet.size() > this.size()) {
            return false
        }

        for (element in otherSet.elements()) {
            if (!this.contains(element)) {
                return false
            }
        }

        return true
    }

    // Method 15: Return a List view of the elements in the MyHashSet
    fun toList(): List<T> {
        return ArrayList(this.map.keySet())
    }

    // Method 16: Add the specified element to the MyHashSet if it is not already present
    fun addIfAbsent(element: T): Boolean {
        if (this.map.containsKey(element)) {
            return false
        }
        this.map.put(element, null)
        this.size++
        return true
    }

    // Method 17: Remove all the elements from the MyHashSet that satisfy the specified predicate
    fun removeIf(predicate: Predicate<T>): Boolean {
        val toRemove = ArrayList<T>()
        for (element in this.elements()) {
            if (predicate.test(element)) {
                toRemove.add(element)
            }
        }

        for (element in toRemove) {
            this.remove(element)
        }

        return true
    }

    // Method 18: Retain only the elements in the MyHashSet that satisfy the specified predicate
    fun retainIf(predicate: Predicate<T>): Boolean {
        val toRemove = ArrayList<T>()
        for (element in this.elements()) {
            if (!predicate.test(element)) {
                toRemove.add(element)
            }
        }

        for (element in toRemove) {
            this.remove(element)
        }

        return true
    }

    // Method 19: Return true if the MyHashSet contains any elements that satisfy the specified predicate
    fun anyMatch(predicate: Predicate<T>): Boolean {
        for (element in this.elements()) {
            if (predicate.test(element)) {
                return true
            }
        }

        return false
    }

    // Method 20: Return true if the MyHashSet contains all elements that satisfy the specified predicate
    fun allMatch(predicate: Predicate<T>): Boolean {
        for (element in this.elements()) {
            if (!predicate.test(element)) {
                return false
            }
        }

        return true
    }


    // Method 21: Equals
    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }

        if (other == null || other !is MyHashSet<*>) {
            return false
        }

        if (other.size() != this.size()) {
            return false
        }

        return this.containsAll(other as MyHashSet<T>)
    }

    // Method 22: HashCode
    override fun hashCode(): Int {
        var result = 0
        for (element in this.elements()) {
            result = 31 * result + element.hashCode()
        }
        return result
    }
}